document.addEventListener("DOMContentLoaded", () => {
    localStorage.setItem('clickedmenu', ".side_history");
})

const calendarDates = document.getElementById('calendarDates');
const currentMonthElement = document.getElementById('currentMonth');
const prevBtn = document.getElementById('prevBtn');
const nextBtn = document.getElementById('nextBtn');

const today = new Date();
let currentMonth = today.getMonth();
let currentYear = today.getFullYear();

/* renderCalendar 함수는 월별 캘랜더를 생성하고 표시하는 함수이다. */
function renderCalendar() {
    const firstDayOfMonth = new Date(currentYear, currentMonth, 1);
    /* 해당 월이 몇 일까지 있는지 알 수 있다. */
    const daysInMonth = new Date(currentYear, currentMonth + 1, 0).getDate();

    /* 현재 월의 첫 번째 날짜의 요일을 나타내는 값을 저장한다.*/
    const startDayOfWeek = firstDayOfMonth.getDay();

    currentMonthElement.textContent = `${currentYear}.${(currentMonth + 1).toString().padStart(2, '0')}`;
    // 월을 나타내는 요소에 현재 월과 연도를 설정하여 표시한다.

    calendarDates.innerHTML = '';

    // 빈 날짜(이전 달)
    const lastDayOfPrevMonth = new Date(currentYear, currentMonth, 0);
    const daysInPrevMonth = lastDayOfPrevMonth.getDate();
    for (let i = startDayOfWeek - 1; i >= 0; i--) {
        const dateElement = document.createElement('div');
        dateElement.classList.add('date', 'empty');
        dateElement.textContent = daysInPrevMonth - i;
        calendarDates.appendChild(dateElement);
    }

    // 현재 달의 날짜
    for (let i = 1; i <= daysInMonth; i++) {
        const dateElement = document.createElement('div');
        const dateContentElement = document.createElement('div')
        const day = document.createElement('p')
        dateElement.classList.add('date');
        dateContentElement.classList.add('date_content')
        day.classList.add("date_day")

        day.textContent = i;
        calendarDates.appendChild(dateElement);
        dateElement.appendChild(day)
        dateElement.appendChild(dateContentElement)

        // 달력에 내용 들어가는 부분 -- 일단은 더미
        if(i === 5) {
            const categoriesElement = document.createElement('p')
            const totalmoneyElement = document.createElement("p")
            let categories = ""
            let totalmoney = 0;

            // 여기는 for문 돌려야 한다.
            categories += "카페, 주유"
            totalmoney += 60000
            // ========================================

            categoriesElement.textContent = categories + "2+"
            totalmoneyElement.textContent =  totalmoney.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "원";

            categoriesElement.classList.add("date_categories")
            totalmoneyElement.classList.add("date_totalmoney")
            dateContentElement.appendChild(categoriesElement)
            dateContentElement.appendChild(totalmoneyElement)
        }
    }

    // 현재 이후의 날짜
    const lastDayOfMonth = new Date(currentYear, currentMonth, daysInMonth);
    const endDayOfWeek = lastDayOfMonth.getDay();
    const totalCells = 35; // 총 셀의 개수 (5주를 표시하기 위해)
    const remainingDays = totalCells - (startDayOfWeek + daysInMonth); // 남은 셀의 개수

    for (let i = 1; i <= remainingDays; i++) {
        const dateElement = document.createElement('div');
        dateElement.classList.add('date', 'empty');
        dateElement.textContent = i;
        calendarDates.appendChild(dateElement);
    }

}

renderCalendar();

prevBtn.addEventListener('click', () => {
    currentMonth--;
    if (currentMonth < 0) {
        currentMonth = 11;
        currentYear--;
    }
    renderCalendar();
});

nextBtn.addEventListener('click', () => {
    currentMonth++;
    if (currentMonth > 11) {
        currentMonth = 0;
        currentYear++;
    }
    renderCalendar();
});

// 모달창
const dialog = document.querySelector('dialog');
const datelist = document.querySelectorAll('.date')

if(datelist.length > 0){
    datelist.forEach(item => {
        item.addEventListener('click', event => {
            dialog.showModal();
            map.relayout()
        });
    });
}

const closeButton = document.querySelector('.close_btn')
closeButton.addEventListener('click', () => {
    dialog.close();
})


// 카카오 지도 API
var container = document.getElementById('map'),
    options = {
    center: new kakao.maps.LatLng(33.450701, 126.570667),
    level: 3
};

var map = new kakao.maps.Map(container, options);
