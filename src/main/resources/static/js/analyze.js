import {
    getExpenditureTendency,
    getTotalAmountBy12Month,
    getTotalAmountByTime,
    getSavingAmountGoal,
    getTotalCountByTimePeriod } from "./api/analyze.js";

document.addEventListener("DOMContentLoaded", async () => {
    localStorage.setItem('clickedmenu', ".side_analyze");

    // 현재 날짜 기준으로 yearAndMonth와 year 계산
    const now = new Date();
    const year = now.getFullYear().toString();
    const month = (now.getMonth() + 1).toString().padStart(2, '0');
    let yearAndMonth = `${year}-${month}`;

    // 월 선택 요소 초기화
    const monthSelect = document.getElementById("monthSelect");
    console.log(monthSelect);

    // 연간 소비 데이터를 가져와서 소비 내역이 있는 달만 monthSelect에 추가
    const ListBy12month = await getTotalAmountBy12Month(year);
    console.log(ListBy12month);

    if (ListBy12month && monthSelect) {
        const months = [
            '1월', '2월', '3월', '4월', '5월', '6월',
            '7월', '8월', '9월', '10월', '11월', '12월'
        ];
        const monthValues = [
            '01', '02', '03', '04', '05', '06',
            '07', '08', '09', '10', '11', '12'
        ];

        months.forEach((month, index) => {
            if (ListBy12month[month] > 0) {  // 소비 내역이 0이 아닌 달만 추가
                const option = document.createElement("option");
                option.value = monthValues[index];
                option.text = month;
                monthSelect.appendChild(option);
            }
        });

        // 현재 월을 기본 선택으로 설정
        if (monthSelect.querySelector(`option[value="${month}"]`)) {
            monthSelect.value = month;
        } else if (monthSelect.options.length > 0) {
            // 기본 선택된 달이 없으면 첫 번째 옵션을 선택
            monthSelect.value = monthSelect.options[0].value;
            yearAndMonth = `${year}-${monthSelect.value}`;
        }
    } else {
        console.error("연간 소비 데이터를 가져오지 못했습니다.");
    }

    // 차트 인스턴스를 저장할 변수
    let timeChart = null;
    let priceChart = null;

    // 데이터 업데이트 함수
    const updateData = async (yearAndMonth) => {
        console.log("Updating data for:", yearAndMonth);

        // 나의 소비MBTI 정보를 가져와서 화면에 출력
        const MBTI = await getExpenditureTendency(yearAndMonth);
        console.log(MBTI);

        const expenditureType1Element = document.getElementById("expenditureType1");
        const expenditureType2Element = document.getElementById("expenditureType2");
        const mbtiImage = document.getElementById("mbtiImage");

        if (MBTI && MBTI.length >= 2) {
            expenditureType1Element.textContent = MBTI[0].memberExpenditureTendency;
            if(expenditureType1Element.textContent === "플렉서"){
                expenditureType1Element.textContent += " (고지출 소비형)";
            } else {
                expenditureType1Element.textContent += " (저지출 소비형)";
            }

            expenditureType2Element.textContent = MBTI[1].memberExpenditureTendency;
            if(expenditureType2Element.textContent === "써니"){
                expenditureType2Element.textContent += " (Sunny, 낮시간대 소비형)";
            } else {
                expenditureType2Element.textContent += " (Moon, 밤시간대 소비형)";
            }

            // 이미지 파일 이름 생성
            const imageName = `${MBTI[1].memberExpenditureTendency}${MBTI[0].memberExpenditureTendency}.png`;

            // 이미지 경로 설정
            const imagePath = `/img/analyze/${imageName}`;

            // 이미지 요소 업데이트
            mbtiImage.src = imagePath;
            mbtiImage.alt = imageName;
        } else {
            expenditureType1Element.textContent = "소비내역이 없습니다";
            expenditureType2Element.textContent = "소비내역이 없습니다";
            mbtiImage.src = "/img/analyze/default.png";  // 기본 이미지 설정
            mbtiImage.alt = "default";
        }

        // 시간대별 지출 횟수 정보를 가져와서 화면에 출력
        const count1218 = await getTotalCountByTimePeriod(yearAndMonth, 17, 12);
        const count1824 = await getTotalCountByTimePeriod(yearAndMonth, 23, 18);
        console.log(count1218, count1824);

        const timePeriodElement = document.getElementById("timePeriod");
        const totalCountElement = document.getElementById("totalCount");

        if (count1218 && count1824) {
            if (count1218.expenditureTotalCount > count1824.expenditureTotalCount) {
                timePeriodElement.textContent = "12:00 ~ 17:59";
                totalCountElement.textContent = `지출 횟수가 ${count1218.expenditureTotalCount}회 이상이시군요!`;
            } else {
                timePeriodElement.textContent = "18:00 ~ 23:59";
                totalCountElement.textContent = `지출 횟수가 ${count1824.expenditureTotalCount}회 이상이시군요!`;
            }
        } else {
            timePeriodElement.textContent = "소비내역이 없습니다";
            totalCountElement.textContent = "";
        }

        // 절약 금액 목표 정보를 가져와서 화면에 출력
        const savingAmountGoal = await getSavingAmountGoal(yearAndMonth);
        console.log(savingAmountGoal);

        const totalExpenditureElement = document.getElementById("totalExpenditure");
        const savingAmountElement = document.getElementById("savingAmount");

        if (savingAmountGoal) {
            const totalExpenditure = savingAmountGoal.totalExpenditure;
            const savingAmount = savingAmountGoal.savingAmount;
            const targetExpenditure = totalExpenditure + savingAmount;

            totalExpenditureElement.textContent = `목표 지출금액은 ${targetExpenditure.toLocaleString()}원 입니다.`;

            if (savingAmount < 0) {
                savingAmountElement.textContent = `${Math.abs(savingAmount).toLocaleString()}원을 플렉스하셨군요!`;
            } else {
                savingAmountElement.textContent = `${savingAmount.toLocaleString()}원을 절약하셨군요!`;
            }
        } else {
            totalExpenditureElement.textContent = "소비내역이 없습니다";
            savingAmountElement.textContent = "";
        }

        // 시간대별 총 지출 금액 정보를 가져와서 차트에 출력
        const List = await getTotalAmountByTime(yearAndMonth);
        console.log(List);

        // 기존 차트가 있으면 파괴
        if (timeChart) {
            timeChart.destroy();
        }

        if (List && Object.keys(List).length > 0) {
            const keys = Object.keys(List);
            keys.sort((a, b) => parseInt(a.split('-')[0]) - parseInt(b.split('-')[0]));
            const sortedData = keys.map(key => List[key]);

            // 시간대별 지출 금액 차트 옵션
            var timeChartOptions = {
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
                    categories: keys,
                },
                yaxis: {
                    title: {
                        text: '지출금액',
                    },
                    min: 0,
                    max: Math.ceil(Math.max(...sortedData) / 50000) * 50000,
                    tickAmount: 5,
                    labels: {
                        formatter: function (value) {
                            return value.toLocaleString() + "원";
                        }
                    }
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

            // 새 차트를 생성하고 변수에 저장
            timeChart = new ApexCharts(document.querySelector(".time_chart"), timeChartOptions);
            timeChart.render();
        } else {
            document.querySelector(".time_chart").innerHTML = "소비내역이 없습니다";
        }

        // 월별 지출 금액 정보를 가져와서 차트에 출력
        const ListBy12monthForChart = await getTotalAmountBy12Month(year);
        console.log(ListBy12monthForChart);

        // 기존 차트가 있으면 파괴
        if (priceChart) {
            priceChart.destroy();
        }

        if (ListBy12monthForChart) {
            const sortedKeys = [
                '1월', '2월', '3월', '4월', '5월', '6월',
                '7월', '8월', '9월', '10월', '11월', '12월'
            ];

            const sortedDataBy12month = sortedKeys.map(key => ListBy12monthForChart[key]);

            // 월별 지출 금액 차트 옵션
            var priceChartOptions = {
                series: [{
                    name: "지출액",
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
                annotations: {
                    yaxis: [{
                        y: savingAmountGoal ? (savingAmountGoal.totalExpenditure + savingAmountGoal.savingAmount) : 0,
                        borderColor: '#FF4560',
                        label: {
                            borderColor: '#FF4560',
                            style: {
                                color: '#fff',
                                background: '#FF4560',
                            },
                            text: '총 지출 목표',
                        }
                    }]
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
                        colors: ['#f3f3f3', 'transparent'],
                        opacity: 0.5
                    },
                },
                xaxis: {
                    categories: sortedKeys,
                },
                yaxis: {
                    title: {
                        text: '지출액',
                    },
                    min: 0,
                    max: Math.ceil(Math.max(...sortedDataBy12month) / 1000000) * 1000000,
                    tickAmount: 5,
                    labels: {
                        formatter: function (value) {
                            return value.toLocaleString() + "원";
                        }
                    }
                },
            };

            // 새 차트를 생성하고 변수에 저장
            priceChart = new ApexCharts(document.querySelector(".price_chart"), priceChartOptions);
            priceChart.render();
        } else {
            document.querySelector(".price_chart").innerHTML = "소비내역이 없습니다";
        }
    };

    // 초기 데이터 로드
    await updateData(yearAndMonth);

    // 업데이트 버튼 클릭 이벤트
    const updateButton = document.getElementById("updateButton");
    if (updateButton) {
        updateButton.addEventListener("click", async () => {
            const selectedMonth = monthSelect ? monthSelect.value : month;
            console.log(selectedMonth);
            yearAndMonth = `${year}-${selectedMonth}`;
            await updateData(yearAndMonth);
        });
    }
});
