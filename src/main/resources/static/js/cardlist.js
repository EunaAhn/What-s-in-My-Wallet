import * as card from "./api/card.js"

let startNum = 0
let endNum = 0
let localKeyWord = ""

document.addEventListener("DOMContentLoaded", () => {
    localStorage.setItem('clickedmenu', ".side_cardlist");
    startNum = 1
    endNum = 10
    localKeyWord =""
    addCardProductList(localKeyWord)
    cardProductTop()

    // 다른 페이지에서 router 되는 카드 키워드 검색
    const searchWord = localStorage.getItem("searchWord")
    if(searchWord) {
        startNum = 1
        endNum = 10
        localKeyWord = searchWord
        localStorage.setItem("searchWord", "")
        while (cardListContainer.firstChild) {
            cardListContainer.removeChild(cardListContainer.firstChild);
        }
        addCardProductList()
    }
})

// 인기 카드 출력
const cardProductTop = async () => {
    console.log("여기?",await card.getCardProductTop())
}

const cardListContainer = document.querySelector(".card-info-list");

cardListContainer.addEventListener("mouseover", (event) => {
    const cardListItem = event.target.closest(".card-list");
    if (cardListItem) {
        const cardTitle = cardListItem.querySelector(".card-title");
        cardTitle.style.color = "#9288dd";
    }
});

cardListContainer.addEventListener("mouseout", (event) => {
    const cardListItem = event.target.closest(".card-list");
    if (cardListItem) {
        const cardTitle = cardListItem.querySelector(".card-title");
        cardTitle.style.color = "#3b3a45";
    }
});

// 카드 아이템 10개 추가 ( 무한 스크롤 )
const addCardButton = document.querySelector(".add_card_list_btn")

const addCardProductList = async () => {
    const extraCardtProductList = await card.getCardProductList(startNum, endNum, localKeyWord)

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
        feeInfo.textContent =`#${card.membershipFee}`;
        benefitInfo.appendChild(feeInfo);

        const categoryInfo = document.createElement('div');

        const categoryList = card.benefitCategoryList.map(benefit => benefit.benefitName).join(', ');
        categoryInfo.classList.add('card-info');
        categoryInfo.textContent = categoryList;
        benefitInfo.appendChild(categoryInfo);

        cardTextInfo.appendChild(benefitInfo);
    })
    startNum += 10
    endNum += 10
}

addCardButton.addEventListener("click", () => {addCardProductList()})

// 카드 키워드 검색
const hdCardSearch = document.querySelector("#hd_card_search")

hdCardSearch.addEventListener("keydown", (event) => {
    if (event.key === "Enter" && hdCardSearch.value) {
        startNum = 1
        endNum = 10
        localKeyWord = hdCardSearch.value
        while (cardListContainer.firstChild) {
            cardListContainer.removeChild(cardListContainer.firstChild);
        }
        addCardProductList()
        hdCardSearch.value = ""
    }
});

const hdSearchImage = document.querySelector(".hd_search_image")

hdSearchImage.addEventListener("click", () => {
    if (hdCardSearch.value) {
        startNum = 1
        endNum = 10
        localKeyWord = hdCardSearch.value
        while (cardListContainer.firstChild) {
            cardListContainer.removeChild(cardListContainer.firstChild);
        }
        addCardProductList()
        hdCardSearch.value = ""
    }
})