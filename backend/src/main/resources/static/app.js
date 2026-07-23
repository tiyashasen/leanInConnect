const API_BASE_URL = '/api';
const modal = document.querySelector('#modal');

const open = () => {
    modal.classList.add('open');
    document.querySelector('#winMessage').focus();
};

document.querySelector('#openWin').addEventListener('click', open);

document.querySelector('#closeWin').addEventListener('click', () => {
    modal.classList.remove('open');
});

modal.addEventListener('click', (e) => {
    if (e.target === modal) {
        modal.classList.remove('open');
    }
});

const message = document.querySelector('#winMessage');

message.addEventListener('input', () => {
    document.querySelector('#count').textContent =
        `${message.value.length} / 360`;
});

const esc = (text) =>
    text.replace(/[&<>"']/g, (char) => ({
        '&': '&amp;',
        '<': '&lt;',
        '>': '&gt;',
        '"': '&quot;',
        "'": '&#039;'
    })[char]);

const renderWin = (win) => `
    <article class="post feature">
        <div class="post-meta">
            <div class="person violet">TS</div>

            <div>
                <strong>${esc(win.author)}</strong>
                <span>Career Pivot Circle · just now</span>
            </div>

            <button class="more">•••</button>
        </div>

        <p class="post-body">${esc(win.message)}</p>

        <div class="post-actions">
            <button class="like">♡ <b>0</b></button>
            <button>◌ <b>0 comments</b></button>
            <button>↗ Share</button>
        </div>
    </article>
`;

async function loadWins() {
  try {
    const response = await fetch(`${API_BASE_URL}/wins`);

    if (!response.ok) {
      throw new Error('Unable to load community wins.');
    }

    const wins = await response.json();

    document.querySelector('#wins').innerHTML =
      wins.map(renderWin).join('');
  } catch (error) {
    console.error(error);
  }
}

document.querySelector('#winForm').addEventListener('submit', async (e) => {
    e.preventDefault();

    const error = document.querySelector('#error');
    error.textContent = '';

    const button = e.currentTarget.querySelector('button');
    button.disabled = true;
    button.textContent = 'Sharing…';

    try {
        const response = await fetch(`${API_BASE_URL}/wins`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                message: message.value
            })
        });

        const win = await response.json();

        if (!response.ok) {
            throw new Error(win.error);
        }

        document
            .querySelector('#wins')
            .insertAdjacentHTML('afterbegin', renderWin(win));

        message.value = '';
        document.querySelector('#count').textContent = '0 / 360';

        modal.classList.remove('open');

        const toast = document.querySelector('#toast');
        toast.classList.add('show');

        setTimeout(() => {
            toast.classList.remove('show');
        }, 3000);

    } catch (err) {
        error.textContent =
            err.message ||
            'The API is unavailable. Start the Spring Boot backend first.';
    } finally {
        button.disabled = false;
        button.innerHTML = 'Share with your circle <span>→</span>';
    }
});


loadWins();