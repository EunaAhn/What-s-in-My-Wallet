document.addEventListener("DOMContentLoaded", () => {
    localStorage.setItem('clickedmenu', ".side_profile");
    loadCardList();
});

const user_dialog = document.querySelector('.user_modal');
const user = document.querySelector('.user');

user.addEventListener('click', event => {
    user_dialog.showModal();
});

const card_dialog = document.querySelector('.card_modal');
const card = document.querySelector('.card');

card.addEventListener('click', event => {
    card_dialog.showModal();
});

const like_dialog = document.querySelector('.like_modal');
const like = document.querySelector('.like');

let unlikedCardIds = [];  // 좋아요 해제된 카드 ID를 추적
let deletedCardNumbers = [];  // 삭제할 카드 번호를 추적

like.addEventListener('click', async event => {
    const likeCardList = await getCardProductLikeList();
    const likeCardListContainer = document.getElementById('likeCardList');
    likeCardListContainer.innerHTML = ''; // 기존 내용 지우기

    likeCardList.forEach(card => {
        const cardElement = document.createElement('div');
        cardElement.classList.add('like_item');
        cardElement.innerHTML = `
            <img class="like_card_img" src="/img/cardimg/${card.cardImageFileName}.jpg" alt="${card.cardName}"/>
            <div class="like_card_content">
                <p class="like_card_name">${card.cardName}</p>
                <p class="like_card_benefit">${card.benefitSummary.replace(/\n/g, '<br>')}</p>
                <p class="like_card_benefit">${card.membershipFee}</p>
            </div>
            <svg style="cursor:pointer;" width="30" height="30" viewBox="0 0 30 30" fill="none" xmlns="http://www.w3.org/2000/svg" data-id="${card.cardProductId}">
                <path d="M13.8607 24.298L13.8592 24.2967C10.6208 21.3601 8.01343 18.9903 6.20395 16.7765C4.40546 14.5762 3.5 12.6525 3.5 10.625C3.5 7.31598 6.07609 4.75 9.375 4.75C11.2477 4.75 13.0616 5.62696 14.2412 7.00128L15 7.88543L15.7588 7.00128C16.9384 5.62696 18.7523 4.75 20.625 4.75C23.9239 4.75 26.5 7.31598 26.5 10.625C26.5 12.6525 25.5945 14.5762 23.796 16.7765C21.9866 18.9903 19.3792 21.3601 16.1408 24.2967L16.1393 24.298L15 25.3352L13.8607 24.298Z" fill="#9288DD" stroke="#9288DD" stroke-width="2"/>
            </svg>
        `;
        likeCardListContainer.appendChild(cardElement);

        const heartIcon = cardElement.querySelector('svg');
        heartIcon.addEventListener('click', () => {
            const cardId = heartIcon.getAttribute('data-id');
            if (unlikedCardIds.includes(cardId)) {
                unlikedCardIds = unlikedCardIds.filter(id => id !== cardId);
                heartIcon.querySelector('path').setAttribute('fill', '#9288DD');
                console.log(`카드 ID ${cardId} 좋아요 설정`);
            } else {
                unlikedCardIds.push(cardId);
                heartIcon.querySelector('path').setAttribute('fill', '#FFFFFF');
                console.log(`카드 ID ${cardId} 좋아요 해제`);
            }
        });
    });

    like_dialog.showModal();
});

const closeButtons = document.querySelectorAll('.close_btn');
closeButtons.forEach((item) => {
    item.addEventListener('click', () => {
        card_dialog.close();
        user_dialog.close();
        like_dialog.close();
    });
});

document.addEventListener("DOMContentLoaded", function() {
    const deleteButtons = document.querySelectorAll(".card_delete");

    deleteButtons.forEach((button) => {
        button.addEventListener("click", function() {
            const cardItem = button.closest(".card_item");
            const cardNumber = cardItem.querySelector('.card_number').textContent.trim().replace(/ /g, ''); // 카드 번호를 추출하고 공백 제거
            if (cardItem) {
                cardItem.remove();
                deletedCardNumbers.push(cardNumber);
                console.log(`카드 번호 ${cardNumber} 삭제 예정`);
            }
        });
    });

    const saveCardButton = document.querySelector(".save_card");
    saveCardButton.addEventListener("click", async () => {
        for (const cardNumber of deletedCardNumbers) {
            await getRegistrationCardDelete(cardNumber);
            console.log(`카드 번호 ${cardNumber} 삭제됨`);
        }
        deletedCardNumbers = []; // 초기화
        alert("변경된 내용을 저장했습니다.");
        card_dialog.close();
    });
});

const checkboxes = document.querySelectorAll('input[type="checkbox"]');
const categoryList = document.querySelectorAll('.form-input-date-picker-default3');

function handleCheckboxChange() {
    let checkedCheckboxes = [];

    categoryList.forEach(list => {
        const checked = list.querySelectorAll('input:checked');
        checkedCheckboxes = checkedCheckboxes.concat(Array.from(checked));
    });

    if (checkedCheckboxes.length > 3) {
        this.checked = false;
        console.log("최대 3개까지만 선택할 수 있습니다.");
    } else {
        const selectedCategories = checkedCheckboxes.map(checkbox => checkbox.nextElementSibling.textContent);
        console.log("선택된 카테고리:", selectedCategories);
    }
}

checkboxes.forEach(checkbox => {
    checkbox.addEventListener('change', handleCheckboxChange);
});

const hdCardSearch = document.querySelector("#hd_card_search");

hdCardSearch.addEventListener("keydown", (event) => {
    if (event.key === "Enter" && hdCardSearch.value) {
        localStorage.setItem("searchWord", hdCardSearch.value);
        window.location.href = "cardlist";
        hdCardSearch.value = "";
    }
});

const hdSearchImage = document.querySelector(".hd_search_image");

hdSearchImage.addEventListener("click", () => {
    if (hdCardSearch.value) {
        localStorage.setItem("searchWord", hdCardSearch.value);
        window.location.href = "cardlist";
        hdCardSearch.value = "";
    }
});

const urlString = window.location.href;
const url = new URL(urlString);
const statusParam = url.searchParams.get("status");

console.log("status 파라미터 값:", statusParam);

const myHeaders = new Headers();
myHeaders.append("Content-Type", "application/json");
myHeaders.append("Authorization", `Bearer ${localStorage.getItem("access_token")}`);

export const getCardProductLikeList = async () => {
    const requestOptions = {
        method: "GET",
        headers: myHeaders
    };
    try {
        const response = await fetch(`/api/cardproduct/like`, requestOptions);
        const result = await response.json();
        return result.result;
    } catch (error) {
        console.log("getCardProductLike error: ", error);
        return null;
    }
};

export const getCardProducMembertLikeDelete = async (cardId) => {
    const raw = JSON.stringify({
        "cardId": cardId
    });
    const requestOptions = {
        method: "DELETE",
        headers: myHeaders,
        body: raw
    };
    try {
        const response = await fetch(`/api/cardproduct/memberlike`, requestOptions);
        const result = await response.json();
        return result.result;
    } catch (error) {
        console.log("getCardProducMembertLikeDelete error: ", error);
        return null;
    }
};

export const getRegistrationCardDelete = async (cardNumber) => {
    const raw = JSON.stringify({
        "cardNumber": cardNumber
    });
    const requestOptions = {
        method: "DELETE",
        headers: myHeaders,
        body: raw
    };
    try {
        const response = await fetch(`/api/registrationcard/delete`, requestOptions);
        const result = await response.json();
        return result.result;
    } catch (error) {
        console.log("getRegistrationCardDelete error: ", error);
        return null;
    }
};

document.querySelector('.save_like').addEventListener('click', async () => {
    for (const cardId of unlikedCardIds) {
        await getCardProducMembertLikeDelete(cardId);
        console.log(`카드 ID ${cardId} 삭제됨`);
    }
    alert('변경된 내용을 저장했습니다.');
    like_dialog.close();
});

// Function to format card number
function formatCardNumber(cardNumber) {
    return cardNumber.slice(0, 4) + ' **** **** ' + cardNumber.slice(-4);
}

// Function to load and display card list
async function loadCardList() {
    try {
        const cardListContainer = document.querySelector('.card_content');
        const cardList = await getRegistrationcardList();

        console.log("Fetched card list: ", cardList);  // Debugging statement

        cardList.forEach(card => {
            const cardItem = document.createElement('div');
            cardItem.classList.add('card_item');

            cardItem.innerHTML = `
                <div class="card_info">
                    <p class="card_name">${card.cardNickName}</p>
                    <p class="card_number">${formatCardNumber(card.cardNumber)}</p>
                </div>
                <button class="card_delete">삭제</button>
            `;

            // 첫 번째 자식 요소로 삽입
            cardListContainer.insertBefore(cardItem, cardListContainer.firstChild);

            cardItem.querySelector('.card_delete').addEventListener('click', function() {
                const cardNumber = cardItem.querySelector('.card_number').textContent.trim().replace(/ /g, ''); // 카드 번호를 추출하고 공백 제거
                cardItem.remove();
                deletedCardNumbers.push(cardNumber);
                console.log(`카드 번호 ${cardNumber} 삭제 예정`);
            });
        });
    } catch (error) {
        console.log("loadCardList error: ", error);
    }
}

export const getRegistrationcardList = async () => {
    const requestOptions = {
        method: "GET",
        headers: myHeaders
    };
    try {
        const response = await fetch(`/api/registrationcard/list`, requestOptions);
        const result = await response.json();
        return result.result;
    } catch (error) {
        console.log("getRegistrationcardList error: ", error);
        return [];
    }
};
