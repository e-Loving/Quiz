package uz.eloving.quizgame.model

data class ModelCardViewLevel(
    val pic: Int,
    var countOfQuestions: Int,
    val degree: Int,
    val alpha: Float = 0.3F,
    val clickable: Boolean = false
)
