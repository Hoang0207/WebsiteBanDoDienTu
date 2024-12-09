const API_URL = 'https://libretranslate.com/translate'; // URL của LibreTranslate

function translateText(text, targetLang, callback) {
    fetch(API_URL, {
        method: 'POST',
        body: JSON.stringify({
            q: text,
            source: 'vi',
            target: targetLang,
            format: 'text'
        }),
        headers: {
            'Content-Type': 'application/json'
        }
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.json();
    })
    .then(data => {
        callback(data.translatedText);
    })
    .catch(error => console.error('Error:', error));
}

function translatePage(targetLang) {
    const elements = document.querySelectorAll('.translatable'); // Lấy tất cả các phần tử có lớp 'translatable'
    elements.forEach(element => {
        translateText(element.textContent, targetLang, translatedText => {
            element.textContent = translatedText;
        });
    });
}
