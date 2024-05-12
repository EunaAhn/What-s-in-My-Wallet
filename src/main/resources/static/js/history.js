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


// 기본적으로 중심 좌표를 설정하여 지도를 생성합니다.
var container = document.getElementById('map'); // 지도를 담을 영역의 DOM 요소를 가져옵니다.
var options = {
    center: new kakao.maps.LatLng(37.583584616776854, 127.00193938906052), // 기본적인 중심 좌표를 설정합니다.
    level: 5 // 지도의 확대 레벨을 설정합니다. (낮을수록 확대 수준이 높습니다.)
};
var map = new kakao.maps.Map(container, options); // 지도를 생성합니다.

// document.addEventListener("DOMContentLoaded", function () {
//     var REST_API_KEY = "846a14217115ae6027031b39e790b997"; // 여기에 발급받은 Kakao REST API 키를 입력하세요.
//     var query = "서울 종로구 창경궁로 239 다이소 혜화점"; // 검색할 장소의 이름
//
//     // 카카오 지도 API를 통해 다이소 혜화역점의 위치를 검색
//     fetch(`https://dapi.kakao.com/v2/local/search/keyword.json?query=${encodeURIComponent(query)}&x=127.06283102249932&y=37.514322572335935&radius=20000`, {
//         headers: {
//             "Authorization": `KakaoAK ${REST_API_KEY}`
//         }
//     })
//         .then(response => response.json())
//         .then(data => {
//             console.log(data); // API 응답을 콘솔에 출력하여 확인합니다.
//             if (data.documents.length > 0) { // 검색 결과가 있는지 확인합니다.
//                 // 검색 결과 중에서 0번째 장소의 위치 정보를 가져옵니다.
//                 var firstPlace = data.documents[0];
//
//                 // 마커가 표시될 위치입니다
//                 var markerPosition = new kakao.maps.LatLng(firstPlace.y, firstPlace.x);
//
//                 // 기존 지도에 마커를 추가합니다.
//                 var marker = new kakao.maps.Marker({
//                     position: markerPosition
//                 });
//
//                 // 기존 지도에 마커를 표시합니다.
//                 marker.setMap(map);
//
//                 // 마커 클릭 이벤트 리스너 추가
//                 kakao.maps.event.addListener(marker, 'click', function() {
//                     // 마커 클릭 시 실행할 동작을 여기에 작성합니다.
//                     window.open(firstPlace.place_url);
//                 });
//             } else {
//                 console.error('검색 결과가 없습니다.'); // 검색 결과가 없는 경우 에러 메시지를 출력합니다.
//             }
//         })
//         .catch(error => console.error('Error:', error));
// });


// const BaseUrl = "http://localhost:8090"; // 포트에 대한 프로토콜을 추가해야 합니다.
// const myHeaders = new Headers();
// myHeaders.append("Content-Type", "application/json");
//
// const raw = JSON.stringify({
//     "nowDate": "2023-05-04"
// });
//
// // GET 요청에는 body를 포함시킬 수 없습니다. 따라서 requestOptions에서 body 속성을 제거해야 합니다.
// const requestOptions = {
//     method: "GET",
//     headers: myHeaders,
//     redirect: "follow"
// };
//
// // 쿼리 매개변수로 데이터를 전달합니다.
// const queryParams = new URLSearchParams({ "nowDate": "2023-05-04" });
// const url = `${BaseUrl}/api/expenditure/daily?${queryParams}`;
//
// fetch(url, requestOptions)
//     .then((response) => response.text())
//     .then((result) => console.log(result))
//     .catch((error) => console.error(error));


const BaseUrl = "localhost:8090"
const myHeaders = new Headers();
myHeaders.append("Content-Type", "application/json");

const getCardProductList  = () => {
    const raw = JSON.stringify({
        "cardCompanyName": null,
        "benefit": "",
        "cardName": ""
    });
    const requestOptions = {
        method: "GET",
        headers: myHeaders,
        body: raw,
        redirect: "follow"
    };
    fetch(`${BaseUrl}/mydatatest`, requestOptions)
        .then((response) => response.text())
        .then((result) => console.log(result))
        .catch((error) => console.error(error));
}
