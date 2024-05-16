import * as card from "./api/card.js"
import * as utils from "./utils.js"

let startNum = 0
let endNum = 0
let localKeyWord = ""

const companys = ["신한카드", "현대카드", "삼성카드", "국민카드","롯데카드", "하나카드", "우리카드", "NH농협카드","IBK기업은행카드"]

document.addEventListener("DOMContentLoaded", () => {
    localStorage.setItem('clickedmenu', ".side_cardlist");
    startNum = 1
    endNum = 20
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
const favoriteCardList = document.querySelector('.favorite-card-list');

// 인기 카드 출력
const cardProductTop = async () => {
    const cards = await card.getCardProductTop()

    // 각 카드 정보를 순회하며 HTML 동적 생성
    cards.forEach((card) => {
        const cardElement = document.createElement('div');
        cardElement.classList.add('favorite-list');
        favoriteCardList.appendChild(cardElement);

        const imgElement = document.createElement('img');
        imgElement.classList.add('favorite-card-list-img');
        imgElement.src = `/img/cardimg/카드${card.cardProductId}.jpg`;
        cardElement.appendChild(imgElement);

        const cardRect = cardElement.getBoundingClientRect();
        const cardWidth = cardRect.width;

        imgElement.onload = () => {
            const imgWidth = imgElement.width;
            const imgHeight = imgElement.height;
            const aspectRatio = imgWidth / imgHeight;

            if (aspectRatio > 1) {
                imgElement.style.transform = "rotate(85deg)";
                imgElement.style.height =`${cardWidth}px`;
                imgElement.style.width =`${aspectRatio*cardWidth}px`;
            }
            else  {
                imgElement.style.transform = "rotate(-5deg)";
                imgElement.style.height = `${cardWidth/aspectRatio}px`;
                imgElement.style.width = `${cardWidth}px`;
            }
        };

        const nameElement = document.createElement('div');
        nameElement.classList.add('favorite');
        nameElement.textContent = companys[card.cardCompanyId];
        cardElement.appendChild(nameElement);

        cardElement.addEventListener("click", () => {
            window.location.href = `carddetail?id=${card.cardProductId}`;
        })
    })
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
        cardList.id = `${card.cardProductId}`
        cardListContainer.appendChild(cardList);
        console.log()

        const cardImage = document.createElement('img');
        cardImage.classList.add('image-19');
        cardImage.src = `/img/cardimg/카드${card.cardProductId}.jpg`;
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

        cardList.addEventListener('click', () => {
            window.location.href = `carddetail?id=${card.cardProductId}`;
        });
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


const container = document.querySelector(".container")

// Alert 창 사용법예시
const aaaa = document.querySelector('.aaaa')
const customAlert = utils.customAlert("안녕하세요.")
aaaa.addEventListener("click", () => {
    container.appendChild(customAlert)
    customAlert.showModal()
})

// ConFirm창 사용법예시
const bbbb = document.querySelector('.bbbb')
const customConfirm = utils.customConfirm(`${localStorage.getItem("memberName")} 님의 카드를 연결해주세요!`,"등록하기", "다음에 하기")
bbbb.addEventListener("click",() => {
    container.appendChild(customConfirm)
    customConfirm.showModal()
})

const confirmTrueButton = customConfirm.querySelector('.confirm_true');
confirmTrueButton.addEventListener('click', () => {
    customConfirm.close();
    console.log(true)
});

const confirmFalseButton = customConfirm.querySelector('.confirm_false');
confirmFalseButton.addEventListener('click', () => {
    customConfirm.close();
    console.log(false)
});