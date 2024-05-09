document.getElementById('signup').addEventListener('click', function (){
    location.href = "/login";
});

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

