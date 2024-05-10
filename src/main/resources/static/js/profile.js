document.addEventListener("DOMContentLoaded", () => {
    localStorage.setItem('clickedmenu', ".side_profile");
})

const user_dialog = document.querySelector('.user_modal');
const user = document.querySelector('.user')

user.addEventListener('click', event => {
    user_dialog.showModal();
});

const card_dialog = document.querySelector('.card_modal');
const card = document.querySelector('.card')

card.addEventListener('click', event => {
    card_dialog.showModal();
});

const like_dialog = document.querySelector('.like_modal');
const like = document.querySelector('.like')

like.addEventListener('click', event => {
    like_dialog.showModal();
});

const closeButtons = document.querySelectorAll('.close_btn')
closeButtons.forEach((item) => {
    item.addEventListener('click', () => {
        card_dialog.close();
        user_dialog.close();
        like_dialog.close();
    })
})


document.addEventListener("DOMContentLoaded", function() {
    const deleteButtons = document.querySelectorAll(".card_delete");

    // card_delete 버튼에 클릭 이벤트 추가
    deleteButtons.forEach((button) => {
        button.addEventListener("click", function() {
            const cardItem = button.closest(".card_item");
            if (cardItem) {
                cardItem.remove();
            }
        });
    });

    // 수정한 내용 저장하는 로직 필용
    const saveCardButton = document.querySelector(".save_card");
    saveCardButton.addEventListener("click", () => {
        alert("변경된 내용을 저장로직 API 들어오면 추가하기.");
        card_dialog.close()
    });
})


// HTML 요소들을 가져옵니다.
const checkboxes = document.querySelectorAll('input[type="checkbox"]');
const categoryList = document.querySelectorAll('.form-input-date-picker-default3');

// 체크박스를 체크할 때마다 호출될 함수를 정의합니다.
function handleCheckboxChange() {
    let checkedCheckboxes = [];

    // 모든 카테고리 목록에서 선택된 체크박스를 가져옵니다.
    categoryList.forEach(list => {
        const checked = list.querySelectorAll('input:checked');
        checkedCheckboxes = checkedCheckboxes.concat(Array.from(checked));
    });

    // 최대 3개까지만 선택 가능하도록 합니다.
    if (checkedCheckboxes.length > 3) {
        this.checked = false;
        console.log("최대 3개까지만 선택할 수 있습니다.");
    } else {
        // 선택된 체크박스들의 목록을 콘솔에 출력합니다.
        const selectedCategories = checkedCheckboxes.map(checkbox => checkbox.nextElementSibling.textContent);
        console.log("선택된 카테고리:", selectedCategories);
    }
}

// 각 체크박스에 이벤트 리스너를 추가합니다.
checkboxes.forEach(checkbox => {
    checkbox.addEventListener('change', handleCheckboxChange);
});