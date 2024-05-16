import * as card from "./api/card.js"

export const customAlert = (notification) => {
    const dialog = document.createElement('dialog');
    dialog.className = 'alert_container';

    const alertWrapper = document.createElement('div');
    alertWrapper.className = 'alert_wrapper';
    dialog.appendChild(alertWrapper);

    const alertContent = document.createElement('div');
    alertContent.className = 'alert_content';
    alertContent.textContent = notification;
    alertWrapper.appendChild(alertContent);

    const alertCheckButton = document.createElement('button');
    alertCheckButton.className = 'alert_check';
    alertCheckButton.textContent = '확인';
    alertWrapper.appendChild(alertCheckButton);

    alertCheckButton.addEventListener('click', () => {
        dialog.close();
    });
    return dialog
}

export const customConfirm = (notification, tureContent, falseContent) => {
    const dialog = document.createElement('dialog');
    dialog.className = 'confirm_container';

    const confirmWrapper = document.createElement('div');
    confirmWrapper.className = 'confirm_wrapper';
    dialog.appendChild(confirmWrapper);

    const confirmContent = document.createElement('div');
    confirmContent.className = 'confirm_content';
    confirmContent.textContent = notification;
    confirmWrapper.appendChild(confirmContent);

    const confirmButtons = document.createElement('div');
    confirmButtons.className = 'confirm_buttons';
    confirmWrapper.appendChild(confirmButtons);

    const confirmTrueButton = document.createElement('button');
    confirmTrueButton.className = 'confirm_true';
    confirmTrueButton.textContent = tureContent;

    confirmButtons.appendChild(confirmTrueButton);

    const confirmFalseButton = document.createElement('button');
    confirmFalseButton.className = 'confirm_false';
    confirmFalseButton.textContent = falseContent;

    confirmButtons.appendChild(confirmFalseButton);
    return dialog
}

document.addEventListener("DOMContentLoaded", () => {
    // goCardProductRegister()
})


const container = document.querySelector(".container")

// 카드 등록 페이지로 라우팅 ( 모든 페이지에 적용 )
// 일단 주석처리
// const goCardProductRegister = async () => {
//     const registeredCard = await card.getRegistrationCardList()
//     if(!registeredCard.length){
//         const customAlertwindow = customAlert(`${localStorage.getItem("memberName")} 님의 카드를 연결해주세요!`)
//         container.appendChild(customAlertwindow)
//         customAlertwindow.showModal()
//         const alertCheckButton = customAlertwindow.querySelector('.alert_check');
//         alertCheckButton.addEventListener('click', () => {
//             customAlertwindow.close();
//             location.href = "cardregistration";
//         });
//     }
// }