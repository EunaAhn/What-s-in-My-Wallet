document.addEventListener("DOMContentLoaded", () => {
    localStorage.setItem('clickedmenu', ".side_cardlist");
})



document.addEventListener('DOMContentLoaded', function() {
    var cardLists = document.querySelectorAll('.card-list');

    cardLists.forEach(function(card) {
        card.addEventListener('click', function() {
            // 상세 페이지 URL 생성
            var cardDetailUrl = 'carddetail';

            // 상세 페이지로 이동
            window.location.href = cardDetailUrl;
        });
    });
});