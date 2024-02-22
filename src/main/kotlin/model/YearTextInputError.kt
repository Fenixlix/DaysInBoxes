package model

enum class YearTextInputError(val message: String, val isWrong: Boolean) {
    DifferentThan4Digits("The year must be of 4 digits", true),
    NotOnlyNumbers("The year must have of only numbers", true),
    OutOfRange("The accepted years are between 1600 and 9999", true),
    AcceptedYear("Valid year :)", false)
}