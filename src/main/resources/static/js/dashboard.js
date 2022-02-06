const botaoInserir = document.querySelectorAll(".btn");

const modal_inserir = document.querySelector(".modal-inserir");
const modal_excluir = document.querySelector(".modal-excluir");
const modal_compras = document.querySelector(".modal-compras");
const modal_pagamento = document.querySelector(".modal-pagamento");

const botaoCancelar = document.querySelectorAll(".btn-cancelar");
const botaoExcluirCartao = document.querySelectorAll(".excluir");
const botaoConsultar = document.querySelectorAll(".btn-consultar");
const botaoExcluir = document.querySelectorAll(".btn-excluir");

const body = document.querySelector("body");
const texto = document.querySelector(".modal-inserir h2");

function modalInserir() {
    botaoInserir.forEach((item) => {
        item.addEventListener("click", (event) => {
            event.preventDefault();

            modal_inserir.style.display = "block";
            modal_compras.style.display = "none";
            modal_excluir.style.display = "none";
            modal_pagamento.style.display = "none";

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
            modal_compras.style.display = "none";
            modal_pagamento.style.display = "none";
        })
    });
}
modalInserir();

function modalConsultar() {
    botaoConsultar.forEach((item) => {
        item.addEventListener("click", (event) => {
            event.preventDefault();

            modal_inserir.style.display = "none";
            modal_excluir.style.display = "none";
            modal_compras.style.display = "none";
            modal_pagamento.style.display = "none";
        })
    });
}
modalConsultar();

function modalExcluir() {
    botaoExcluir.forEach((item) => {
        item.addEventListener("click", (event) => {
            event.preventDefault();

            modal_excluir.style.display = "block";
            modal_inserir.style.display = "none";
            modal_compras.style.display = "none";
            modal_pagamento.style.display = "none";
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

function modalCadastrarPix() {
    const botao = document.querySelector(".cadastrarPix");
    const modal = document.querySelector(".modalCadastroPix");

    if(modal != null) {
        botao.addEventListener("click", (event) => {
            event.preventDefault();
    
            modal.style.display = "block";
        });
    } else {
        console.log("Erro");
    }
}
modalCadastrarPix();

function modalContratarSeguro() {
    const botao = document.querySelector(".botao-contratar");
    const modal = document.querySelector(".modal-seguro");
 
    if (modal != null) {
        botao.addEventListener("click", (event) => {
            event.preventDefault();
    
            modal.style.display = "block";
        });
    } else {
        console.log("Erro");
    }
}
modalContratarSeguro();

function modalExcluirApolice() {
    const botao = document.querySelectorAll(".botao-excluirSeguro");
    const modal = document.querySelector(".modalCancelarApolice");

    if (modal != null) {
        botao.forEach((item) => {
            item.addEventListener("click", (event) => {
                event.preventDefault();

                modal.style.display = "block";
            });
        });
    } else {
        console.log("Erro");
    }
}
modalExcluirApolice();

function fecharModal() {
    const cancelar = document.querySelectorAll(".botao-cancelar");
    const modalPix = document.querySelector(".modalCadastroPix");
    const modalApolice = document.querySelector(".modalCancelarApolice");

    if(modalPix != null) {
        cancelar.forEach((item) => {
            item.addEventListener("click", (event) => {
                event.preventDefault();
        
                modalPix.style.display = "none";
            });
        });
    } else if (modalApolice != null) {
        cancelar.forEach((item) => {
            item.addEventListener("click", (event) => {
                event.preventDefault();
        
                modalApolice.style.display = "none";
            });
        });
    } else {
        console.log("Erro");
    }
}
fecharModal();

function gerarPdfFatura() {
    const botaoFatura = document.querySelector(".botao-pagarFatura");
    
    var doc = new jsPDF();
        doc.text(`Recebedor: Banco NEXT`, 5, 30);
        doc.text(`CNPJ Recebedor: 22.333.444/0001-55`, 5, 50);
        doc.text(`Pagante: Gabriel Nunes`, 5, 70);
        doc.text(`CPF Pagante: 222.333.444-55`, 5, 90);
        doc.text(`Valor: R$ 6.000,00`, 5, 110);
        doc.text(`Endereço: Av. Brasil, 277 - CEP: 012345-678 - Jardins - SP`, 5, 130);
        doc.text(`|||| |||| |||| |||| |||| |||| |||| |||| |||| |||| |||| |||| |||| |||| |||| |||| |||| |||| |||| ||||`, 25, 150);
        doc.text(`|||| |||| |||| |||| |||| |||| |||| |||| |||| |||| |||| |||| |||| |||| |||| |||| |||| |||| |||| ||||`, 25, 155);
        doc.save("Comprovante_Compra.pdf");
}