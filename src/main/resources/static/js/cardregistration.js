import * as card from "./api/card.js"
const companys = ["신한카드", "현대카드", "삼성카드", "국민카드","롯데카드", "하나카드", "우리카드", "NH농협카드","IBK기업은행카드"]
let cardItemlist = []

document.addEventListener("DOMContentLoaded", async () => {
    cardItemlist = await card.getMyCard()
    console.log(cardItemlist)
    await generateCardInfo(cardItemlist)
})

// 개인이 소지한 카드목록 불러오기
const generateCardInfo = (cardItemlist) => {
    const cardInfoList = document.querySelector('.card-info-list');

    cardItemlist.forEach((item, index) => {
        console.log(item)
        const cardList = document.createElement('div');
        cardList.classList.add('card-list');

        const cardImage = document.createElement('img');
        cardImage.classList.add('image-19');
        cardImage.src = '/img/cardlist/card1.png';
        cardList.appendChild(cardImage);

        const cardTextInfo = document.createElement('div');
        cardTextInfo.classList.add('card-text-info');

        // 현재 카드사로 되어있는데 카드 타이틀로 변경해야함
        const cardTitle = document.createElement('div');
        cardTitle.classList.add('card-title');
        cardTitle.textContent = `${item.cardName}`;
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
    const cardList = document.querySelectorAll(".card-list");
    const selectedCardIds = [];
    cardList.forEach(card => {
        const checkbox = card.querySelector('input[type="checkbox"]');
        if (checkbox && checkbox.checked) {
            selectedCardIds.push({"cardNumber":checkbox.id}); // or checkbox.id if you prefer id
        }
    });
    return selectedCardIds;
}

const registerButton = document.getElementById('register')
registerButton.addEventListener('click', async () => {
    const selectedCardIds = registerRegistrationCardList()
    const registerCardListVal = await card.postRegistrationcardRegister(selectedCardIds)
    if(registerCardListVal === true) {
        alert(`${selectedCardIds.length}개의 카드가 등록되었습니다.`)
    }
    location.href = "cardlist";
});

