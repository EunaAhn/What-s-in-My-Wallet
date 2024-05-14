import * as card from "./api/card.js"
let cardItemlist = []

document.addEventListener("DOMContentLoaded", async () => {
    cardItemlist = await card.getRegistrationCardList()
    await generateCardInfo(cardItemlist)
})

// 개인이 소지한 카드목록 불러오기
const generateCardInfo = (cardItemlist) => {
    const cardInfoList = document.querySelector('.card-info-list');

    cardItemlist.forEach((item, index) => {
        const cardList = document.createElement('div');
        cardList.classList.add('card-list');

        const cardImage = document.createElement('img');
        cardImage.classList.add('image-19');
        cardImage.src = '/img/cardlist/card1.png';
        cardList.appendChild(cardImage);

        const cardTextInfo = document.createElement('div');
        cardTextInfo.classList.add('card-text-info');

        const cardTitle = document.createElement('div');
        cardTitle.classList.add('card-title');
        cardTitle.textContent = `${item.cardNickName}`;
        cardTextInfo.appendChild(cardTitle);

        const benefitInfo = document.createElement('div');
        benefitInfo.classList.add('benefit-info');

        const cardInfo = document.createElement('div');
        cardInfo.classList.add('card-info');
        cardInfo.textContent = `${item.cardNumber.substring(0, 4)}-****-****-${item.cardNumber.substring(12, 16)}`;
        benefitInfo.appendChild(cardInfo);

        cardTextInfo.appendChild(benefitInfo);
        cardList.appendChild(cardTextInfo);

        const checkbox = document.createElement('input');
        checkbox.setAttribute('type', 'checkbox');
        checkbox.setAttribute('name', 'card-selection');
        checkbox.setAttribute('value', `card${index + 1}`);
        checkbox.id = item.cardNumber; // 카드 번호를 id 값으로 설정
        cardList.appendChild(checkbox);
        cardInfoList.appendChild(cardList);
    });
};

// 선택한 카드 등록하기
const registerRegistrationCardList = () => {

}

const registerButton = document.getElementById('register')
registerButton.addEventListener('click', function (){
    registerRegistrationCardList()
    // location.href = "/cardlist";
});

