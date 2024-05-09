document.addEventListener("DOMContentLoaded", () => {
    localStorage.setItem('clickedmenu', ".side_analyze");
})

var options = {
    series: [{
        name: 'Times',
        data: [44, 55, 41, 67, 22, 43, 21, 33, 45, 31, 87, 65]
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
        categories : [
            '00 ~ 02', '02 ~ 04', '04 ~ 06', '06 ~ 08',
            '08 ~ 10', '10 ~ 12', '12 ~ 14', '14 ~ 16',
            '16 ~ 18', '18 ~ 20', '20 ~ 22', '22 ~ 00'
        ],
        // tickPlacement: 'on'
    },
    yaxis: {
        title: {
            text: 'Times',
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
        data: [10, 41, 35, 51, 49, 62, 69, 91, 91, 62, 69, 91, 91]
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