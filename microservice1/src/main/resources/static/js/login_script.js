document.addEventListener('DOMContentLoaded', () => {
    document.getElementById('loginForm').addEventListener('submit', (e) => {
        e.preventDefault();
        authFunction();
    });

});


async function authFunction(){
    const username = document.getElementById('first').value;
    const password = document.getElementById('password').value;

    const response = await fetch('http://localhost:8081/api/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ username: username, password: password})
    });

    if (response.ok) {
        // Redirect to /analytics
        window.location.href = '/api/analytics';
    } else {
        const error = await response.text();
        alert(error);
    }
}


