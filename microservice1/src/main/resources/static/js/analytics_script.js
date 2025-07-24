document.addEventListener('DOMContentLoaded', () => {
    const image = document.getElementById('analyticsGraph');
    document.getElementById('runAnalyticsBtn').addEventListener('click', () => showGraph(image));
});

function showGraph(image){
    image.src = '/api/analytics/graph?ts=' + new Date().getTime();
    image.style.display = "block";
}