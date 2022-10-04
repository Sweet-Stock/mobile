package sweet.apisweetstore.enums

enum class ResetPasswordMessage(message: String) {
    SAME_PASSWORD(message = "Senha atual e antiga iguais!"),
    CURRENT_PASSWORD_INCORRECT(message = "Senha atual incorreta!"),
    CONFIRMATION_PASSWORD_INCORRECT(message = "Confirmacao de senha incorreta!"),
    RESET_SUCCESS(message = "Reset de senha efetuado com sucesso!")
}