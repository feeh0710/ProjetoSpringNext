const botaoInserir = document.querySelectorAll(".btn");
const modalInserir = document.querySelector(".modal-inserir");
const botaoCancelar = document.querySelectorAll(".btn-cancelar");
const botaoFinalizar = document.querySelector(".btn-finalizarModal");
const body = document.querySelector("body");
const texto = document.querySelector(".modal-inserir h2");

function abrirModal() {
    botaoInserir.forEach((item) => {
        item.addEventListener("click", (event) => {
            event.preventDefault();

            modalInserir.style.display = "block";

            texto.innerHTML = item.innerHTML;
            body.scrollIntoView({
                behavior: "smooth",
                block: "start",
            });
        });
    });

    botaoCancelar.forEach((item) => {
        item.addEventListener("click", (event) => {
            event.preventDefault();

            modalInserir.style.display = "none";
        })
    });

    botaoFinalizar.addEventListener("click", (event) => {
        event.preventDefault();

        modalInserir.style.display = "none";
    });
}
abrirModal();