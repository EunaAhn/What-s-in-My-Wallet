import * as suggestion from "./api/suggestion.js";

document.addEventListener("DOMContentLoaded", () => {
    localStorage.setItem('clickedmenu', ".side_suggestion");
    CardRecommandation()
})
const CardRecommandation = async () => {
    await suggestion.getCardReco()
}

const yearAndMonth = "2024-05"
const categoryList = await suggestion.getCategoryNameList(yearAndMonth);
const expenditureRatio = await suggestion.getExpenditureRatioList(yearAndMonth);

var options_pie = {
    series: expenditureRatio,
    chart: {
        width: 380,
        type: 'pie',
    },
    labels: categoryList,
    responsive: [{
        breakpoint: 480,
        options_pie: {
            chart: {
                height: "100%",
                width: "100%"
            },
            legend: {
                position: 'bottom'
            }
        }
    }]
};

var piechart = new ApexCharts(document.querySelector("#pie-chart"), options_pie);
piechart.render();


const CATEGORYNAME = await suggestion.getLineCategoryNameList(yearAndMonth);
const TOTALAMOUNT = await suggestion.getTotalAmountList(yearAndMonth);

var options_line = {
    series: [
        {
            name: "Low - 2013",
            data: TOTALAMOUNT
        }
    ],
    chart: {
        height: "100%",
        width: "100%",
        type: 'line',
        dropShadow: {
            enabled: true,
            color: '#000',
            top: 18,
            left: 7,
            blur: 10,
            opacity: 0.2
        },
        zoom: {
            enabled: false
        },
        toolbar: {
            show: false
        }
    },
    colors: ['#77B6EA', '#545454'],
    dataLabels: {
        enabled: true,
    },
    stroke: {
        curve: 'smooth'
    },
    grid: {
        borderColor: '#e7e7e7',
        row: {
            colors: ['#f3f3f3', 'transparent'], // takes an array which will be repeated on columns
            opacity: 0.5
        },
    },
    markers: {
        size: 1
    },
    xaxis: {
        categories: CATEGORYNAME,
        title: {
            text: '카테고리'
        }
    },
    yaxis: {
        title: {
            text: '지출횟수'
        },
        min: 0,
        max: 20
    },
    legend: {
        position: 'top',
        horizontalAlign: 'right',
        floating: true,
        offsetY: -25,
        offsetX: -5
    }
};

var linechart = new ApexCharts(document.querySelector("#line-chart"), options_line);
linechart.render();

// const img = document.querySelector('.img');
//
// img.onload = function() {
//     // 이미지의 가로와 세로 크기 가져오기
//     const width = img.width;
//     const height = img.height;
//
//     // 가로세로 비율 계산
//     const aspectRatio = width / height;
//
//     // 결과 출력
//     console.log('Width:', width);
//     console.log('Height:', height);
//     console.log('Aspect Ratio:', aspectRatio);
// };
//
// if (img.complete) {
//     img.onload();
// }