import * as card from "./api/card.js"

document.addEventListener("DOMContentLoaded", () => {
    localStorage.setItem('clickedmenu', ".side_cardlist");
    addCardProductList()
})

const cardListContainer = document.querySelector(".card-info-list");

// 카드 아이템 hover 시 title 색상에 하이w라이팅
cardListContainer.addEventListener("mouseover", (event) => {
    if (event.target.classList.contains("card-list")) {
        const cardTitle = event.target.querySelector(".card-title");
        cardTitle.style.color = "#9288dd";
    }
});

cardListContainer.addEventListener("mouseout", (event) => {
    if (event.target.classList.contains("card-list")) {
        const cardTitle = event.target.querySelector(".card-title");
        cardTitle.style.color = "#3b3a45";
    }
});

// 카드 아이템 10개 추가 ( 무한 스크롤 )
const addCardButton = document.querySelector(".add_card_list_btn")

const addCardProductList = async () => {
    console.log("?????")
    const extraCardtProductList = await card.getCardProductList()
    extraCardtProductList.forEach(card => {
        const cardList = document.createElement('div');
        cardList.classList.add('card-list');
        cardListContainer.appendChild(cardList);

        const cardImage = document.createElement('img');
        cardImage.classList.add('image-19');
        cardImage.src = '/img/cardlist/card1.png';
        cardList.appendChild(cardImage);

        const cardTextInfo = document.createElement('div');
        cardTextInfo.classList.add('card-text-info');
        cardList.appendChild(cardTextInfo);

        const cardTitle = document.createElement('div');
        cardTitle.classList.add('card-title');
        cardTitle.textContent = card.cardName;
        cardTextInfo.appendChild(cardTitle);

        const benefitInfo = document.createElement('div');
        benefitInfo.classList.add('benefit-info');

        const summaryInfo = document.createElement('div');
        summaryInfo.classList.add('card-info');
        summaryInfo.textContent = card.benefitSummary;
        benefitInfo.appendChild(summaryInfo);

        const feeInfo = document.createElement('div');
        feeInfo.classList.add('card-info');
        feeInfo.textContent = card.membershipFee;
        benefitInfo.appendChild(feeInfo);

        const categoryInfo = document.createElement('div');

        const categoryList = card.benefitCategoryList.map(benefit => benefit.benefitName).join(', ');
        categoryInfo.classList.add('card-info');
        categoryInfo.textContent = categoryList;
        benefitInfo.appendChild(categoryInfo);

        cardTextInfo.appendChild(benefitInfo);
    })
}

addCardButton.addEventListener("click", () => {addCardProductList()})


