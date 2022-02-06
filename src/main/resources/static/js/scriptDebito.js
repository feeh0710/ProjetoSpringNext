const botaoInserir = document.querySelectorAll(".btn");

const modal_inserir = document.querySelector(".modal-inserir");
const modal_excluir = document.querySelector(".modal-excluir");

const botaoCancelar = document.querySelectorAll(".btn-cancelar");
const botaoExcluirCartao = document.querySelectorAll(".excluir");
const botaoFinalizarCompra = document.querySelector(".btn-pagar");
const botaoFinalizar = document.querySelector(".btn-finalizarModal");
const botaoConsultar = document.querySelectorAll(".btn-consultar");
const botaoExcluir = document.querySelectorAll(".btn-excluir");

const body = document.querySelector("body");
const texto = document.querySelector(".modal-inserir h2");

function modalInserir() {
    botaoInserir.forEach((item) => {
        item.addEventListener("click", (event) => {
            event.preventDefault();

            modal_inserir.style.display = "block";
            modal_excluir.style.display = "none";

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

            modal_inserir.style.display = "none";
            modal_excluir.style.display = "none";
        })
    });

    botaoFinalizar.addEventListener("click", (event) => {
        event.preventDefault();

        modal_inserir.style.display = "none";
    });
};
modalInserir();

function modalExcluir() {
    botaoExcluir.forEach((item) => {
        item.addEventListener("click", (event) => {
            event.preventDefault();

            modal_excluir.style.display = "block";
            modal_inserir.style.display = "none";
        })
    });

    botaoExcluirCartao.forEach((item) => {
        item.addEventListener("click", (event) => {
            event.preventDefault();

            modal_excluir.style.display = "none";
        });
    });
}
modalExcluir();

