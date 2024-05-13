import * as history from "./api/history.js"
import {getStoreAddressList} from "./api/history.js";

const nowDate = "2023-05-04";
const addDailyExpenditureList = async () => {
    const dailyList = await history.getDailyExpenditureList(nowDate);
    const expenditureSummaryDtoList = await history.getExpenditureSummaryDtoList(nowDate);
    const addressList = await history.getStoreAddressList(nowDate);
    //console.log(expenditureSummaryDtoList)
    createItems(dailyList);
    createExpenseItems(expenditureSummaryDtoList);
    //console.log(addressList);
    //console.log(dailyList);
}

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

    if (datelist.length > 0) {
        datelist.forEach(item => {
            item.addEventListener('click', event => {
                dialog.showModal();
                //alert("모달창이 열렸습니다."); // 모달창이 열릴 때마다 알림창을 띄웁니다.
                map.relayout(); // map.relayout() 함수 호출
                setBounds(); // 지도를 재배치합니다.
            });
        });
    }

    const closeButton = document.querySelector('.close_btn')
    closeButton.addEventListener('click', () => {
        dialog.close();
    })


var map;
var points = [];

window.addEventListener('popstate', async function (event) {
    // 페이지 이동을 감지하여 처리하는 코드 작성
    // 페이지가 돌아왔을 때 initMap 함수를 다시 호출하여 지도를 재설정합니다.
    console.log(points)
    await initMap();
});

document.addEventListener("DOMContentLoaded", async function () {
    // 초기화 함수를 호출합니다.
    await initMap();
});

async function initMap() {
    var REST_API_KEY = "846a14217115ae6027031b39e790b997"; // 여기에 발급받은 Kakao REST API 키를 입력하세요.

    const addressList = await getStoreAddressList(nowDate);
    //console.log(addressList);

    // 기본적으로 중심 좌표를 설정하여 지도를 생성합니다.
    var container = document.getElementById('map'); // 지도를 담을 영역의 DOM 요소를 가져옵니다.
    var options = {
        center: new kakao.maps.LatLng(37.583584616776854, 127.00193938906052), // 기본적인 중심 좌표를 설정합니다.
        level: 5 // 지도의 확대 레벨을 설정합니다. (낮을수록 확대 수준이 높습니다.)
    };
    map = new kakao.maps.Map(container, options); // 지도를 생성합니다.

    // 주소 목록에 있는 각 주소에 대해 처리합니다.
    await Promise.all(addressList.map(async (item) => {
        var query = item;
        var response = await fetch(`https://dapi.kakao.com/v2/local/search/address.json?query=${encodeURIComponent(query)}`, {
            headers: {
                "Authorization": `KakaoAK ${REST_API_KEY}`
            }
        });
        var data = await response.json();
        //console.log(data); // API 응답을 콘솔에 출력하여 확인합니다.
        if (data.documents.length > 0) { // 검색 결과가 있는지 확인합니다.
            // 검색 결과 중에서 0번째 장소의 위치 정보를 가져옵니다.
            var firstPlace = data.documents[0];

            // 위도와 경도를 배열에 추가합니다.
            var latitude = parseFloat(firstPlace.address.y);
            var longitude = parseFloat(firstPlace.address.x);

            // LatLng 객체를 생성하여 points 배열에 추가합니다.
            var point = new kakao.maps.LatLng(latitude, longitude);
            points.push(point);

            // 마커가 표시될 위치입니다
            var markerPosition = new kakao.maps.LatLng(latitude, longitude);

            // 기존 지도에 마커를 추가합니다.
            var marker = new kakao.maps.Marker({
                position: markerPosition
            });

            // 기존 지도에 마커를 표시합니다.
            marker.setMap(map);

            // 마커 클릭 이벤트 리스너 추가
            kakao.maps.event.addListener(marker, 'click', function () {
                // 마커 클릭 시 실행할 동작을 여기에 작성합니다.
                console.log(firstPlace);
            });
        } else {
            console.error('검색 결과가 없습니다.'); // 검색 결과가 없는 경우 에러 메시지를 출력합니다.
        }
    }));

    // 모든 주소를 처리한 후에 지도 범위를 재설정합니다.
    setBounds();

    // 선을 그립니다.
    drawLine();

    // 포인터 배열 확인을 위해 alert 함수 호출
    //alertPointsArray();
}

// 포인터 배열 확인 함수
// function alertPointsArray() {
//     // 포인터 배열이 비어있는지 확인합니다.
//     if (points.length > 0) {
//         // 포인터 배열의 내용을 문자열로 변환하여 알림창에 표시합니다.
//         var pointsString = points.map(point => `Latitude: ${point.getLat()}, Longitude: ${point.getLng()}`).join('\n');
//         window.alert("포인터 배열 내용:\n" + pointsString);
//     } else {
//         window.alert("포인터 배열이 비어 있습니다.");
//     }
// }

function setBounds() {
    // points 배열이 비어있는지 확인합니다.
    //console.log(points)

    // LatLngBounds 객체를 생성하고 points 배열에 저장된 좌표들을 기준으로 범위를 설정합니다.
    var bounds = new kakao.maps.LatLngBounds();
    points.forEach(point => bounds.extend(point));

    // LatLngBounds 객체에 추가된 좌표들을 기준으로 지도의 범위를 재설정합니다
    // 이때 지도의 중심좌표와 레벨이 변경될 수 있습니다
    map.setBounds(bounds);
}

function drawLine() {
    // Polyline을 생성하고, points 배열에 저장된 좌표들을 연결합니다.
    var polyline = new kakao.maps.Polyline({
        path: points, // 선을 구성하는 좌표 배열입니다
        strokeWeight: 3, // 선의 두께입니다
        strokeColor: '#47acd5', // 선의 색깔입니다
        strokeOpacity: 0.7, // 선의 불투명도입니다
        strokeStyle: 'solid' // 선의 스타일입니다
    });

    // 지도에 선을 표시합니다
    polyline.setMap(map);
}



// expenditureSummaryDtoList를 반복하면서 동적으로 expense_item을 생성하는 함수
function createItems(dailyList) {
    const main = document.querySelector('.main');
    const memo  =  document.querySelector('.memo_box');

    const boxprice = document.createElement('div');
    boxprice.classList.add('main_box', 'price'); // Add classes 'main_box' and 'price'

    const titleElement = document.createElement('p');
    titleElement.classList.add('title');
    titleElement.textContent = `총 지출금액`;
    boxprice.appendChild(titleElement);

    const contentElement = document.createElement('div');
    contentElement.classList.add('content');
    contentElement.textContent = `${dailyList.expenditureTotalAmount}원`;
    boxprice.appendChild(contentElement); // Append contentElement, not titleElement

//--------------------------

    const cardinfo = document.createElement('div');
    cardinfo.classList.add('main_box', 'cardinfo'); // Add classes 'main_box' and 'price'

    const expendNumTitleElement = document.createElement('p');
    expendNumTitleElement.classList.add('title');
    expendNumTitleElement.textContent = `결제 횟수`;
    cardinfo.appendChild(expendNumTitleElement);

    const expendContentElement = document.createElement('div');
    expendContentElement.classList.add('content');
    expendContentElement.textContent = `${dailyList.totalExpenditureCount}회`;
    cardinfo.appendChild(expendContentElement); // Append contentElement, not titleElement

    //--------------------------

    const memoTitleElement = document.createElement('p');
    memoTitleElement.classList.add('title');
    memoTitleElement.textContent = `메모`;
    memo.appendChild(memoTitleElement);

    const memoContentElement = document.createElement('input');
    memoContentElement.classList.add('content', 'memo_content');
    memoContentElement.type = 'text';
    memoContentElement.value = `${dailyList.memo}`;
    memo.appendChild(memoContentElement); // Append contentElement, not titleElement

    main.appendChild(boxprice);
    main.appendChild(cardinfo);

}


// expenditureSummaryDtoList를 반복하면서 동적으로 expense_item을 생성하는 함수
function createExpenseItems(expenditureSummaryDtoList) {
    const expenseList = document.querySelector('.expense_list'); // expense_list 요소를 선택합니다.


    expenditureSummaryDtoList.forEach(item => { // expenditureSummaryDtoList를 반복합니다.
        // expense_item 요소를 생성합니다.
        const expenseItem = document.createElement('div');
        expenseItem.classList.add('expense_item');

        // 소비 금액을 나타내는 요소를 생성하고 추가합니다.
        const priceElement = document.createElement('p');
        priceElement.classList.add('expense_price');
        priceElement.textContent = `-${item.expenditureAmount}원`;
        expenseItem.appendChild(priceElement);

        // 가게 이름을 나타내는 요소를 생성하고 추가합니다.
        const placeElement = document.createElement('p');
        placeElement.classList.add('expense_place');
        placeElement.textContent = item.storeName;
        expenseItem.appendChild(placeElement);

        // 소비 일시와 카드 정보를 나타내는 요소를 생성하고 추가합니다.
        const dateCardElement = document.createElement('p');
        dateCardElement.classList.add('expense_date_card');
        dateCardElement.textContent = item.expenditureDatetime;
        expenseItem.appendChild(dateCardElement);

        // expenseList에 생성된 expense_item을 추가합니다.
        expenseList.appendChild(expenseItem);
    });
}


// addDailyExpenditureList 함수를 호출하여 데이터를 가져와서 expense_item을 동적으로 생성합니다.
addDailyExpenditureList();


// 카드 키워드 검색
const hdCardSearch = document.querySelector("#hd_card_search")

hdCardSearch.addEventListener("keydown", (event) => {
    if (event.key === "Enter" && hdCardSearch.value) {
        localStorage.setItem("searchWord", hdCardSearch.value)
        window.location.href = "cardlist"
        hdCardSearch.value = ""
    }
});


const hdSearchImage = document.querySelector(".hd_search_image")

hdSearchImage.addEventListener("click", () => {
    if (hdCardSearch.value) {
        localStorage.setItem("searchWord", hdCardSearch.value)
        window.location.href = "cardlist"
        hdCardSearch.value = ""
    }
})