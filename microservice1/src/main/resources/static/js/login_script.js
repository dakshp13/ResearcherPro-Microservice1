document.addEvenListener('DOMContentLoaded', () => {
    document.getElementById('submitBtn').addEventListener('click', authFunction);
});


async function authFunction(){
    const response = await fetch('http://localhost:8081/api/login' , {
        method: 'POST'
    })

}