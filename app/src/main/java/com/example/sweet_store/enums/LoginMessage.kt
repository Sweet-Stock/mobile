package sweet.apisweetstore.enums

enum class LoginMessage(message: String) {
    EMAIL_NOT_EXIST(message = "Email não cadastrado!"),
    WRONG_PASSWORD(message = "Senha incorreta!"),
    LOGIN_SUCESS(message = "Login efetuado com sucesso!")
}