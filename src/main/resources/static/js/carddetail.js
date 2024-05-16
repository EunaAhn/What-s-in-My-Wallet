import * as card from "./api/card.js"

let cardId;

document.addEventListener("DOMContentLoaded", () => {
    const url = new URL(window.location.href);
    cardId = new URLSearchParams(url.search).get('id');
    cardDetail()
})

// 카드 상세 정보 조회
const cardDetail = async () => {
    const cardDetailItem = await card.postCardDetail(cardId)
    console.log(cardDetailItem)
    const cardImage = document.querySelector(".image-9")
    const likeCount = document.querySelector(".likeCount")
    const likeButton = document.querySelector(".likeButton")
    const cardDetailTitle = document.querySelector(".card-detail-title")
    const cardMembershipFee = document.querySelector(".card-membershipFee")
    const cardBaserecord = document.querySelector(".card-baserecord")
    const cardBenefitSummary = document.querySelector(".card-benefitSummary")
    const imageWrapper = document.querySelector(".imagewrapper")

    if(cardDetailItem.isUserClickLike === 1) {
        likeButton.querySelector('path').setAttribute('fill', '#9288DD');
    }

    likeCount.innerHTML = cardDetailItem.likeCount
    cardImage.src = `/img/cardimg/카드${cardDetailItem.cardProductId}.jpg`;
    cardDetailTitle.innerHTML = cardDetailItem.cardName
    cardMembershipFee.innerHTML = cardDetailItem.membershipFee
    cardBaserecord.innerHTML = cardDetailItem.baseRecord
    cardBenefitSummary.innerHTML =cardDetailItem.benefitSummary

    const cardRect = imageWrapper.getBoundingClientRect();
    const cardWidth = cardRect.width;

    const cardDetailContainer = document.querySelector(".card-detail-container")

    cardImage.onload = () => {
        const imgWidth = cardImage.width;
        const imgHeight = cardImage.height;
        const aspectRatio = imgWidth / imgHeight;

        if (aspectRatio > 1) {
            cardImage.style.transform = "rotate(85deg)";
            cardImage.style.height =`${cardWidth}px`;
            cardImage.style.width =`${aspectRatio*cardWidth}px`;
            imageWrapper.style.height =`${aspectRatio*cardWidth+10}px`;
            // console.log(aspectRatio ," : ",aspectRatio*cardWidth)
            // console.log(aspectRatio ," : ",cardWidth)
            // console.log(aspectRatio ," : ",aspectRatio*cardWidth+60)
        }
        else  {
            cardImage.style.transform = "rotate(-5deg)";
            cardImage.style.height = `${cardWidth/aspectRatio}px`;
            cardImage.style.width = `${cardWidth}px`;
            // console.log(aspectRatio ," : ",cardWidth/aspectRatio)
            // console.log(aspectRatio ," : ",cardWidth)
        }
    };

    const cardInfoList = document.querySelector(".card-info-list")
    cardDetailItem.benefitCategoryList.forEach((benefitCategory) => {
        const cardListDiv = document.createElement('div');
        cardListDiv.classList.add('card-list');
        cardInfoList.appendChild(cardListDiv)

        const image = document.createElement('img');
        image.classList.add('image-19');
        image.src = "/img/carddetail/healthicons_money-bag-outline.svg";
        cardListDiv.appendChild(image);

        const cardTextInfoDiv = document.createElement('div');
        cardTextInfoDiv.classList.add('card-text-info_box');
        cardListDiv.appendChild(cardTextInfoDiv);

        const cardTitleDiv = document.createElement('div');
        cardTitleDiv.classList.add('card-title');
        cardTitleDiv.textContent = `${benefitCategory.benefitName}`;
        cardTextInfoDiv.appendChild(cardTitleDiv);

        const benefitInfoDiv = document.createElement('div');
        benefitInfoDiv.classList.add('benefit-info');

        benefitCategory.benefitDetailsList.forEach((benefitDetailitems) => {
            const cardInfoDiv = document.createElement('div');
            cardInfoDiv.classList.add('card-info');
            cardInfoDiv.innerHTML = `${benefitDetailitems.benefitDetails}`;
            benefitInfoDiv.appendChild(cardInfoDiv);
        })
        cardTextInfoDiv.appendChild(benefitInfoDiv);
    })
}

const likeButton = document.querySelector(".likeButton")

likeButton.addEventListener("click", async () => {
    const likeCount = document.querySelector(".likeCount")
    const likeButton = document.querySelector(".likeButton svg path")
    const cardDetailItem = await card.postCardDetail(cardId)

    if (cardDetailItem.isUserClickLike === 0){
        const likeCard = await card.postLikeCard(cardId)
        console.log("찜하기 성공여부: ",likeCard)
        if(likeCard === true){
            likeCount.innerHTML = cardDetailItem.likeCount + 1
            likeButton.setAttribute('fill', '#9288DD');
        }
    }
    else {
        const likeCard = await card.deleteLikeCard(cardId)
        console.log("찜취소 성공여부: ",likeCard)
        if(likeCard === true){
            likeCount.innerHTML = cardDetailItem.likeCount - 1
            likeButton.setAttribute('fill', '#FFFFFF');
        }
    }
})
















