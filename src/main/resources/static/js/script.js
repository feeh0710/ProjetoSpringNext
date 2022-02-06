const botaoLogin = document.querySelector("#btn-log");
const botaoCadastro = document.querySelector("#btn-add");
const login = document.querySelector(".log");
const cadastroDados = document.querySelector(".formBasico");
const cadastroEnde = document.querySelector(".formEnd");
const botaoAvancar = document.querySelector(".btn-avancar");
const botaoFinalizar = document.querySelector(".btn-finalizar");
const botaoVoltar = document.querySelector(".btn-voltar");

function alternarConteudo() {
    if (botaoLogin != null) {
        botaoLogin.addEventListener("click", (event) => {
            event.preventDefault();
    
            document.title = "Banco Next | Home";
            botaoLogin.style.fontWeight = "bold";
            botaoCadastro.style.fontWeight = "normal";
            login.style.display = "block";
            cadastroDados.style.display = "none";
            cadastroEnde.style.display = "none";
        });
    }

    if (botaoCadastro != null) {
        botaoCadastro.addEventListener("click", (event) => {
            event.preventDefault();
    
            document.title = "Banco Next | Cadastro";
            botaoCadastro.style.fontWeight = "bold";
            botaoLogin.style.fontWeight = "normal";
            cadastroDados.style.display = "block";
            login.style.display = "none";
            cadastroEnde.style.display = "none";
        });
    }
}
alternarConteudo();

function avancarCadastro() {
    if (botaoAvancar != null) {
        botaoAvancar.addEventListener("click", (event) => {
            event.preventDefault();
    
            login.style.display = "none";
            cadastroDados.style.display = "none";
            cadastroEnde.style.display = "block";
        });
    }
 
    if (botaoFinalizar != null) {
        botaoFinalizar.addEventListener("click", (event) => {
            event.preventDefault();
    
            login.style.display = "block";
            cadastroDados.style.display = "none";
            cadastroEnde.style.display = "none";
            document.title = "Banco Next | Login";
            botaoCadastro.style.fontWeight = "normal";
            botaoLogin.style.fontWeight = "bold";
        });
    }

    if (botaoVoltar != null) {
        botaoVoltar.addEventListener("click", (event) => {
            event.preventDefault();
    
            login.style.display = "none";
            cadastroDados.style.display = "block";
            cadastroEnde.style.display = "none";
        });
    }
}
avancarCadastro();

function modalExcluirPix() {
    const botao = document.querySelectorAll(".btn-excluirPix");
    const modal = document.querySelector(".modalCancelarPix");

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
modalExcluirPix();

function fecharModal() {
    const cancelar = document.querySelectorAll(".botao-cancelar");
    const modal = document.querySelector(".modalCancelarPix");

    if(modal != null) {
        cancelar.forEach((item) => {
            item.addEventListener("click", (event) => {
                event.preventDefault();
        
                modal.style.display = "none";
            });
        });
    } else {
        console.log("Erro");
    }
}
fecharModal();

function formatarCampos() {
    const cpfInformado = document.querySelectorAll(".cpf");
    const senha = document.querySelector(".senhaLogin");

    cpfInformado.forEach((item) => {
        item.addEventListener("change", () => {
            const cpf = item.value;
            const novoCPF = cpf.replace(/(\d{3})(\d{3})(\d{3})(\d{2})/g, "$1.$2.$3-$4");
            item.value = novoCPF;            
        });
    });
}
formatarCampos();
