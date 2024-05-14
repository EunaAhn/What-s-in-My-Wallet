document.addEventListener("DOMContentLoaded", function() {
    const sideMenus = document.querySelectorAll(".side_menu");

    sideMenus.forEach(function(sideMenu) {
        const clickedmenu =  document.querySelector(localStorage.getItem("clickedmenu"))
        clickedmenu.classList.add("clicked_side_menu");

        sideMenus.forEach(function(menu) {
            if (menu !== clickedmenu && menu.classList.contains("clicked_side_menu")) {
                menu.classList.remove("clicked_side_menu");
            }
        });


        const faSolidIcon = clickedmenu.querySelector(".fa-solid");
        const sideMenuWord = clickedmenu.querySelector(".side_menu_word");

        faSolidIcon.classList.add("clicked_fa-solid");
        sideMenuWord.classList.add("clicked_side_menu_word");


        sideMenus.forEach(function(menu) {
            if (menu !== clickedmenu) {
                const otherFaSolidIcon = menu.querySelector(".fa-solid");
                const otherSideMenuWord = menu.querySelector(".side_menu_word");
                otherFaSolidIcon.classList.remove("clicked_fa-solid");
                otherSideMenuWord.classList.remove("clicked_side_menu_word");
            }
        });
    });
});
