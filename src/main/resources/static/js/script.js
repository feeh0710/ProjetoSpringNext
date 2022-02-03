const botaoLogin = document.querySelector("#btn-log");
const botaoCadastro = document.querySelector("#btn-add");
const login = document.querySelector(".log");
const cadastroDados = document.querySelector(".formBasico");
const cadastroEnde = document.querySelector(".formEnd");
const botaoAvancar = document.querySelector(".btn-avancar");
const botaoFinalizar = document.querySelector(".btn-finalizar");
const botaoVoltar = document.querySelector(".btn-voltar");

function alternar(){

        document.title = "Banco Next | Cadastro";
        botaoCadastro.style.fontWeight = "bold";
        botaoLogin.style.fontWeight = "normal";
        cadastroDados.style.display = "block";
        login.style.display = "none";
        cadastroEnde.style.display = "none";
}

function alternarConteudo() {
    botaoLogin.addEventListener("click", (event) => {
        event.preventDefault();

        document.title = "Banco Next | Home";
        botaoLogin.style.fontWeight = "bold";
        botaoCadastro.style.fontWeight = "normal";
        login.style.display = "block";
        cadastroDados.style.display = "none";
        cadastroEnde.style.display = "none";
    });

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
alternarConteudo();

function avancarCadastro() {
    botaoAvancar.addEventListener("click", (event) => {
        event.preventDefault();

        login.style.display = "none";
        cadastroDados.style.display = "none";
        cadastroEnde.style.display = "block";
    });

    botaoFinalizar.addEventListener("click", (event) => {
        event.preventDefault();

        login.style.display = "block";
        cadastroDados.style.display = "none";
        cadastroEnde.style.display = "none";
        document.title = "Banco Next | Login";
        botaoCadastro.style.fontWeight = "normal";
        botaoLogin.style.fontWeight = "bold";
    });

    botaoVoltar.addEventListener("click", (event) => {
        event.preventDefault();

        login.style.display = "none";
        cadastroDados.style.display = "block";
        cadastroEnde.style.display = "none";
    });
}
avancarCadastro();

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