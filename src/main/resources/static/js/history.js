import * as history from "./api/history.js";
import { getExpenditureList, getStoreAddressList, getExpenditureKeywordList, getDailyExpenditureMemo, getNewExpenditureList } from "./api/history.js";

document.addEventListener("DOMContentLoaded", () => {
    localStorage.setItem('clickedmenu', ".side_history");
})

// 현재 날짜를 기준으로 nowDate와 yearAndMonth 생성
const today = new Date();
const year = today.getFullYear();
const month = String(today.getMonth() + 1).padStart(2, '0');
const day = String(today.getDate()).padStart(2, '0');

const yearAndMonth = `${year}-${month}`;

// 모달창
const dialog = document.querySelector('dialog');

// 전역 변수로 선택된 키워드를 저장
let selectedKeyword = null;
let currentMemoId = null;

const addDailyExpenditureList = async (currentYear, currentMonth, selectedDay) => {
    const year = String(currentYear);
    const month = String(currentMonth).padStart(2, '0');
    const day = String(selectedDay).padStart(2, '0');
    const nowDate = `${year}-${month}-${day}`;
    currentMemoId = nowDate;

    try {
        const dailyList = await history.getDailyExpenditureList(nowDate) || {};
        const expenditureSummaryDtoList = await history.getExpenditureSummaryDtoList(nowDate) || [];

        if (!dailyList.expenditureTotalAmount && expenditureSummaryDtoList.length === 0) {
            alert("소비내역이 없습니다.");
            return;
        }

        document.querySelector('.main').innerHTML = '';
        document.querySelector('.memo_box').innerHTML = '';
        document.querySelector('.map_box').innerHTML = '';
        document.querySelector('.expense_list').innerHTML = '';
        dialog.showModal();

        createItems(dailyList, nowDate);
        createExpenseItems(expenditureSummaryDtoList);
    } catch (error) {
        console.error('Error fetching data:', error);
    }
};

document.addEventListener("DOMContentLoaded", () => {
    const calendarDates = document.getElementById('calendarDates');
    const currentMonthElement = document.getElementById('currentMonth');
    const prevBtn = document.getElementById('prevBtn');
    const nextBtn = document.getElementById('nextBtn');
    const categoriesSelect = document.getElementById('categories');
    const closeButton = document.querySelector('.close_btn');
    const saveButton = document.querySelector('.modal_save');
    const updateButton = document.getElementById('updateBtn');

    let currentMonth = today.getMonth();
    let currentYear = today.getFullYear();

    categoriesSelect.addEventListener('change', () => {
        selectedKeyword = categoriesSelect.options[categoriesSelect.selectedIndex].value === '전체' ? null : categoriesSelect.options[categoriesSelect.selectedIndex].text;
        console.log(selectedKeyword);
        addCalendarData(currentMonth, currentYear, selectedKeyword);
    });

    prevBtn.addEventListener('click', () => {
        currentMonth--;
        if (currentMonth < 0) {
            currentMonth = 11;
            currentYear--;
        }
        addCalendarData(currentMonth, currentYear, selectedKeyword); // 현재 선택된 키워드를 유지
    });

    nextBtn.addEventListener('click', () => {
        currentMonth++;
        if (currentMonth > 11) {
            currentMonth = 0;
            currentYear++;
        }
        addCalendarData(currentMonth, currentYear, selectedKeyword); // 현재 선택된 키워드를 유지
    });

    closeButton.addEventListener('click', () => {
        dialog.close();
    });

    saveButton.addEventListener('click', async () => {
        const memoContentElement = document.querySelector('.memo_content');
        const memo = memoContentElement.value;
        try {
            const result = await getDailyExpenditureMemo(currentMemoId, memo);
            if (result) {
                alert("메모가 성공적으로 저장되었습니다.");
            } else {
                alert("메모 저장에 실패했습니다.");
            }
        } catch (error) {
            console.error('Error saving memo:', error);
        }
    });

    updateButton.addEventListener('click', async () => {
        try {
            const newExpenditureList = await getNewExpenditureList();
            if (newExpenditureList) {
                console.log("내역이 업데이트되었습니다.", newExpenditureList);
                alert("내역이 업데이트되었습니다.");
                // 새로운 소비 내역을 기반으로 캘린더를 업데이트합니다.
                addCalendarData(currentMonth, currentYear, selectedKeyword);
            } else {
                console.log("내역 업데이트에 실패했습니다.");
                alert("내역 업데이트에 실패했습니다.");
            }
        } catch (error) {
            console.error('Error updating expenditure list:', error);
            alert('Error updating expenditure list');
        }
    });

    addCalendarData(currentMonth, currentYear);
});

const addCalendarData = async (currentMonth, currentYear, keyword = null) => {
    const year = currentYear;
    const month = String(currentMonth + 1).padStart(2, '0');
    const yearAndMonth = `${year}-${month}`;

    try {
        let expenditureList;
        if (keyword) {
            expenditureList = await getExpenditureKeywordList(yearAndMonth, keyword);
        } else {
            expenditureList = await history.getExpenditureList(yearAndMonth);
        }

        if (expenditureList.categoryList.length === 0 && expenditureList.expenditureList.length === 0) {
            console.log('No expenditure data found for the given keyword.');
        }

        const expenditureCategoryNameList = expenditureList.categoryList.map(item => {
            return item.categoryNameList.map(category => category.categoryName);
        });

        const expenditureDayList = expenditureList.categoryList.map(item => {
            const date = new Date(item.expenditureDatetime);
            return date.getDate().toString();
        });

        const expenditureAmounts = expenditureList.expenditureList.reduce((acc, item) => {
            acc[new Date(item.expenditureDate).getDate()] = item.expenditureAmount;
            return acc;
        }, {});

        const categoryNameList = expenditureDayList.map((day, index) => [day, expenditureCategoryNameList[index]]);

        renderCalendarCurrentMonth(categoryNameList, expenditureAmounts, currentYear, currentMonth, keyword);
    } catch (error) {
        console.error('Error fetching calendar data:', error);
        renderCalendar(currentYear, currentMonth);
    }
};

function renderCalendarCurrentMonth(categoryNameList, expenditureAmounts, currentYear, currentMonth, keyword = null) {
    const firstDayOfMonth = new Date(currentYear, currentMonth, 1);
    const daysInMonth = new Date(currentYear, currentMonth + 1, 0).getDate();
    const startDayOfWeek = firstDayOfMonth.getDay();
    const currentMonthElement = document.getElementById('currentMonth');
    const calendarDates = document.getElementById('calendarDates');

    currentMonthElement.textContent = `${currentYear}.${(currentMonth + 1).toString().padStart(2, '0')}`;
    calendarDates.innerHTML = '';

    const lastDayOfPrevMonth = new Date(currentYear, currentMonth, 0);
    const daysInPrevMonth = lastDayOfPrevMonth.getDate();
    for (let i = startDayOfWeek - 1; i >= 0; i--) {
        const dateElement = document.createElement('div');
        dateElement.classList.add('date', 'empty');
        dateElement.textContent = daysInPrevMonth - i;
        calendarDates.appendChild(dateElement);
    }

    for (let i = 1; i <= daysInMonth; i++) {
        const dateElement = document.createElement('div');
        const dateContentElement = document.createElement('div');
        const day = document.createElement('p');
        const categoriesElement = document.createElement('p');
        const amountElement = document.createElement('p');

        dateElement.classList.add('date');
        dateContentElement.classList.add('date_content');
        day.classList.add("date_day");

        day.textContent = i;

        const categoryNames = getCategoryNamesForDate(i, categoryNameList, keyword);
        categoriesElement.textContent = categoryNames;

        const expenditureAmount = expenditureAmounts[i];
        amountElement.textContent = (categoryNames || expenditureAmount) ? `${expenditureAmount ? expenditureAmount.toLocaleString() : ''}원` : '';

        calendarDates.appendChild(dateElement);
        dateElement.appendChild(day);
        dateElement.appendChild(dateContentElement);
        dateContentElement.appendChild(categoriesElement);
        dateContentElement.appendChild(amountElement);

        dateElement.addEventListener('click', () => {
            if (!categoryNames && !expenditureAmount) {
                alert("소비내역이 없습니다.");
            } else {
                addDailyExpenditureList(currentYear, currentMonth + 1, i);
            }
        });
    }

    const lastDayOfMonth = new Date(currentYear, currentMonth, daysInMonth);
    const endDayOfWeek = lastDayOfMonth.getDay();
    const totalCells = 35;
    const remainingDays = totalCells - (startDayOfWeek + daysInMonth);

    for (let i = 1; i <= remainingDays; i++) {
        const dateElement = document.createElement('div');
        dateElement.classList.add('date', 'empty');
        dateElement.textContent = i;
        calendarDates.appendChild(dateElement);
    }
}

function getCategoryNamesForDate(date, categoryNameList, keyword = null) {
    const categoryNameArray = [];
    categoryNameList.forEach(([day, categories]) => {
        if (day === date.toString()) {
            if (keyword) {
                categories.forEach(category => {
                    if (category === keyword) {
                        categoryNameArray.push(category);
                    }
                });
            } else {
                categoryNameArray.push(...categories);
            }
        }
    });
    return categoryNameArray.join(', ');
}

function renderCalendar(currentYear, currentMonth) {
    const firstDayOfMonth = new Date(currentYear, currentMonth, 1);
    const daysInMonth = new Date(currentYear, currentMonth + 1, 0).getDate();
    const startDayOfWeek = firstDayOfMonth.getDay();
    const currentMonthElement = document.getElementById('currentMonth');
    const calendarDates = document.getElementById('calendarDates');

    currentMonthElement.textContent = `${currentYear}.${(currentMonth + 1).toString().padStart(2, '0')}`;
    calendarDates.innerHTML = '';

    const lastDayOfPrevMonth = new Date(currentYear, currentMonth, 0);
    const daysInPrevMonth = lastDayOfPrevMonth.getDate();
    for (let i = startDayOfWeek - 1; i >= 0; i--) {
        const dateElement = document.createElement('div');
        dateElement.classList.add('date', 'empty');
        dateElement.textContent = daysInPrevMonth - i;
        calendarDates.appendChild(dateElement);
    }

    for (let i = 1; i <= daysInMonth; i++) {
        const dateElement = document.createElement('div');
        const dateContentElement = document.createElement('div');
        const day = document.createElement('p');
        dateElement.classList.add('date');
        dateContentElement.classList.add('date_content');
        day.classList.add("date_day");

        day.textContent = i;
        calendarDates.appendChild(dateElement);
        dateElement.appendChild(day);
        dateElement.appendChild(dateContentElement);

        dateElement.addEventListener('click', () => {
            alert("소비내역이 없습니다.");
        });
    }

    const lastDayOfMonth = new Date(currentYear, currentMonth, daysInMonth);
    const endDayOfWeek = lastDayOfMonth.getDay();
    const totalCells = 35;
    const remainingDays = totalCells - (startDayOfWeek + daysInMonth);

    for (let i = 1; i <= remainingDays; i++) {
        const dateElement = document.createElement('div');
        dateElement.classList.add('date', 'empty');
        dateElement.textContent = i;
        calendarDates.appendChild(dateElement);
    }
}

var map;
var points = [];

async function initMap(nowDate) {
    var REST_API_KEY = "846a14217115ae6027031b39e790b997"; // 여기에 발급받은 Kakao REST API 키를 입력하세요.

    try {
        const addressList = await history.getStoreAddressList(nowDate);

        points = [];

        var container = document.getElementById('map');
        var options = {
            center: new kakao.maps.LatLng(37.583584616776854, 127.00193938906052),
            level: 5
        };
        map = new kakao.maps.Map(container, options);

        await Promise.all(addressList.map(async (item) => {
            points = [];

            var query = item;
            var response = await fetch(`https://dapi.kakao.com/v2/local/search/keyword.json?query=${encodeURIComponent(query)}`, {
                headers: {
                    "Authorization": `KakaoAK ${REST_API_KEY}`
                }
            });
            var data = await response.json();
            if (data.documents.length > 0) {
                var firstPlace = data.documents[0];

                var latitude = parseFloat(firstPlace.y);
                var longitude = parseFloat(firstPlace.x);

                var point = new kakao.maps.LatLng(latitude, longitude);
                points.push(point);

                var markerPosition = new kakao.maps.LatLng(latitude, longitude);

                var marker = new kakao.maps.Marker({
                    position: markerPosition
                });

                marker.setMap(map);

                kakao.maps.event.addListener(marker, 'click', function () {
                    console.log(firstPlace);
                });
            } else {
                console.error('검색 결과가 없습니다.');
            }
        }));

        setBounds();
        drawLine();
    } catch (error) {
        console.error('Error initializing map:', error);
    }
}

function setBounds() {
    var bounds = new kakao.maps.LatLngBounds();
    points.forEach(point => bounds.extend(point));
    map.setBounds(bounds);
}

function drawLine() {
    var polyline = new kakao.maps.Polyline({
        path: points,
        strokeWeight: 3,
        strokeColor: '#47acd5',
        strokeOpacity: 0.7,
        strokeStyle: 'solid'
    });
    polyline.setMap(map);
}

function createItems(dailyList, nowDate) {
    const main = document.querySelector('.main');
    const memo = document.querySelector('.memo_box');
    const map_box = document.querySelector('.map_box');

    const boxprice = document.createElement('div');
    boxprice.classList.add('main_box', 'price');

    const titleElement = document.createElement('p');
    titleElement.classList.add('title');
    titleElement.textContent = `총 지출금액`;
    boxprice.appendChild(titleElement);

    const contentElement = document.createElement('div');
    contentElement.classList.add('content');
    contentElement.textContent = `${(dailyList.expenditureTotalAmount || 0).toLocaleString()}원`;
    boxprice.appendChild(contentElement);

    const mapTitleElement = document.createElement('p');
    mapTitleElement.classList.add('title');
    mapTitleElement.textContent = `오늘의 소비지도`;
    map_box.appendChild(mapTitleElement);

    const mapElement = document.createElement('div');
    mapElement.id = 'map';
    map_box.appendChild(mapElement);

    const cardinfo = document.createElement('div');
    cardinfo.classList.add('main_box', 'cardinfo');

    const expendNumTitleElement = document.createElement('p');
    expendNumTitleElement.classList.add('title');
    expendNumTitleElement.textContent = `결제 횟수`;
    cardinfo.appendChild(expendNumTitleElement);

    const expendContentElement = document.createElement('div');
    expendContentElement.classList.add('content');
    expendContentElement.textContent = `${(dailyList.totalExpenditureCount || 0).toLocaleString()}회`;
    cardinfo.appendChild(expendContentElement);

    const memoTitleElement = document.createElement('p');
    memoTitleElement.classList.add('title');
    memoTitleElement.textContent = `메모`;
    memo.appendChild(memoTitleElement);

    const memoContentElement = document.createElement('input');
    memoContentElement.classList.add('content', 'memo_content');
    memoContentElement.type = 'text';
    memoContentElement.value = `${dailyList.memo || ''}`;
    memo.appendChild(memoContentElement);

    main.appendChild(boxprice);
    main.appendChild(cardinfo);

    initMap(nowDate);
}

function createExpenseItems(expenditureSummaryDtoList) {
    const expenseList = document.querySelector('.expense_list');

    expenditureSummaryDtoList.forEach(item => {
        const expenseItem = document.createElement('div');
        expenseItem.classList.add('expense_item');

        const priceElement = document.createElement('p');
        priceElement.classList.add('expense_price');
        priceElement.textContent = `-${item.expenditureAmount.toLocaleString()}원`;
        expenseItem.appendChild(priceElement);

        const placeElement = document.createElement('p');
        placeElement.classList.add('expense_place');
        placeElement.textContent = item.storeName;
        expenseItem.appendChild(placeElement);

        const dateCardElement = document.createElement('p');
        dateCardElement.classList.add('expense_date_card');
        dateCardElement.textContent = item.expenditureDatetime;
        expenseItem.appendChild(dateCardElement);

        expenseList.appendChild(expenseItem);
    });
}
