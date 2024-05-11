const data = [
    {
        "cardProductId": 1,
        "cardCompanyId": "1",
        "cardName": "더드림신한카드",
        "cardImageFileName": "더드림신한카드",
        "membershipFee": "국내 10000원",
        "benefitSummary": "한번에 하나씩 편의점 1%할인",
        "likeCount": 4,
        "benefitCategoryList": [
            {
                "benefitName": "언제나할인"
            },
            {
                "benefitName": "주유"
            }
        ]
    },
    {
        "cardProductId": 2,
        "cardCompanyId": "2",
        "cardName": "마일리지카드",
        "cardImageFileName": "마일리지카드",
        "membershipFee": "국내 20000원",
        "benefitSummary": "마일리지 1%적립",
        "likeCount": 6,
        "benefitCategoryList": [
            {
                "benefitName": "항공"
            },
            {
                "benefitName": "호텔"
            }
        ]
    },
    {
        "cardProductId": 3,
        "cardCompanyId": "3",
        "cardName": "롯데카드",
        "cardImageFileName": "롯데카드",
        "membershipFee": "국내 15000원",
        "benefitSummary": "롯데백화점 5%할인",
        "likeCount": 8,
        "benefitCategoryList": [
            {
                "benefitName": "백화점"
            },
            {
                "benefitName": "마트"
            }
        ]
    }
]

document.addEventListener("DOMContentLoaded", () => {
    localStorage.setItem('clickedmenu', ".side_cardlist");
})

const cardListContainer = document.querySelector(".card-info-list");

// 카드 아이템 hover 시 title 색상에 하이라이팅
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

addCardButton.addEventListener("click", () => {
    data.forEach(card => {
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
    });
})


