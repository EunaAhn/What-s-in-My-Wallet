import * as analyze from "./api/analyze.js"
import {geTotalAmountBy12month, getTotalAmountBytime} from "./api/analyze.js";

document.addEventListener("DOMContentLoaded", () => {
    localStorage.setItem('clickedmenu', ".side_analyze");
})

const yearAndMonth = "2024-05"

const List = await getTotalAmountBytime(yearAndMonth);
// 결과 객체에서 키를 배열로 가져옵니다.
const keys = Object.keys(List);

// 키를 오름차순으로 정렬합니다.
keys.sort((a, b) => parseInt(a.split('-')[0]) - parseInt(b.split('-')[0]));

// 정렬된 키 배열을 이용하여 결과 값을 리스트로 만듭니다.
const sortedData = keys.map(key => ( List[key] ));


const ListBy12month = await geTotalAmountBy12month(yearAndMonth);
const keys2 = Object.keys(ListBy12month);
// 키를 오름차순으로 정렬합니다.
keys2.sort((a, b) => parseInt(a.split('월')[0]) - parseInt(b.split('월')[0]));

// 정렬된 키 배열을 이용하여 결과 값을 리스트로 만듭니다.
const sortedDataBy12month = keys2.map(key => ListBy12month[key]);

var options = {
    series: [{
        name: '지출금액',
        data: sortedData
    }],
    annotations: {
        points: [{
            x: 'Bananas',
            seriesIndex: 0,
            label: {
                borderColor: '#775DD0',
                offsetY: 0,
                style: {
                    color: '#fff',
                    background: '#775DD0',
                },
                text: 'Bananas are good',
            }
        }]
    },
    chart: {
        height: "100%",
        width: "100%",
        type: 'bar',
    },
    plotOptions: {
        bar: {
            columnWidth: '40%',
        }
    },
    dataLabels: {
        enabled: false
    },
    stroke: {
        width: 0
    },
    grid: {
        row: {
            colors: ['#fff', '#f2f2f2']
        }
    },
    xaxis: {
        labels: {
            rotate: -45
        },
        categories : keys,
        // tickPlacement: 'on'
    },
    yaxis: {
        title: {
            text: '지출금액',
        },
    },
    fill: {
        type: 'gradient',
        gradient: {
            shade: 'light',
            type: "horizontal",
            shadeIntensity: 0.25,
            gradientToColors: undefined,
            inverseColors: true,
            opacityFrom: 0.85,
            opacityTo: 0.85,
            stops: [50, 0, 100]
        },
    }
};

var timechart = new ApexCharts(document.querySelector(".time_chart"), options);
timechart.render();


var options = {
    series: [{
        name: "Desktops",
        data: sortedDataBy12month
    }],
    chart: {
        height: "100%",
        width: "100%",
        type: 'line',
        zoom: {
            enabled: false
        }
    },
    dataLabels: {
        enabled: false
    },
    stroke: {
        curve: 'smooth'
    },
    title: {
        align: 'left'
    },
    grid: {
        row: {
            colors: ['#f3f3f3', 'transparent'], // takes an array which will be repeated on columns
            opacity: 0.5
        },
    },
    xaxis: {
        categories: [
            '01월', '02월', '03월', '04월',
            '05월', '06월', '07월', '08월',
            '09월', '10월', '11월', '12월'
        ],
    },
    yaxis: {
        title: {
            text: '지출액',
        },
        min: 0,
        max: 100,
        tickAmount: 5,
    },

};

var pricechart = new ApexCharts(document.querySelector(".price_chart"), options);
pricechart.render();