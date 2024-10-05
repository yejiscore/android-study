fun main(args: Array<String>) {
    // 학생 수 입력
    println("Enter the number of students:")
    val numOfStudents:Int = readlnOrNull()?.toIntOrNull()?:0

    // id, 성적을 저장할 배열 선언
    val idArray = arrayOfNulls<Long>(numOfStudents)
    val scoreArray = arrayOfNulls<Int>(numOfStudents)

    // 각 학생의 id, 성적 입력
    for (i in 0 until numOfStudents) {
        println("Enter ID for student ${i+1}:")
        idArray[i] = readLine()?.toLongOrNull()

        println("Enter score for student ${i+1}:")
        scoreArray[i] = readLine()?.toIntOrNull()

        // null
        if (idArray[i] == null || scoreArray[i] == null) {
            println("Unknown")
        }
    }

    // 평균 구하기
    // 유효한 값만 넣기
    val validScores = scoreArray.filterNotNull()
    val sum = validScores.sum()
    // 유효한 점수가 없다면 null -> 평균 계산 때 Unknown 출력할 예정
    val avgScore = if (validScores.isNotEmpty()) sum.toFloat() / validScores.size else null

    // find the highest and lowest score
    var highestScore: Int? = null
    var lowestScore: Int? = null
    var highestId: Long? = null
    var lowestId: Long? = null

    for (i in 0 until numOfStudents) {
        val currentScore = scoreArray[i]
        val currentId = idArray[i]

        if (currentScore == null || currentId == null) continue

        // find the highest score
        if (highestScore == null || currentScore > highestScore) {
            highestScore = currentScore
            highestId = currentId
        }

        // find the lowest score
        if (lowestScore == null || currentScore < lowestScore) {
            lowestScore = currentScore
            lowestId = currentId
        }
    }

    // 평균 출력
    if (avgScore != null) {
        println("Avg. score:$avgScore")
    } else { // 유효한 값이 아니라면
        println("Unknown")
    }

    // 가장 성적이 좋은 학생의 점수와 id 출력
    if (highestId != null && highestScore != null) {
        println("Highest score -> ID: $highestId Score: $highestScore")
    } else {
        println("Unknown")
    }

    // 가장 성적이 낮은 학생의 점수와 id 출력
    if (lowestId != null && lowestScore != null) {
        println("Lowest score -> ID: $lowestId Score: $lowestScore")
    } else {
        println("Unknown")
    }
}