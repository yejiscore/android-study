import kotlin.math.sqrt

typealias Point = List<Int>
typealias PointList = List<Point>

val pt:(Int, Int) -> List<Int> = {a, b -> listOf(a, b) }
val default_func = { points:PointList -> points }

// 1. point list 와 processor function 를 인자로 받는 함수
fun process(points: PointList, processor: (PointList) -> Any = default_func) {
    val result = processor(points)
    println(result)
}

// 2. X 좌표가 음수인 점
fun filterLeft(points: PointList): PointList {
    return points.filter { it[0] < 0 }
}

// 3. norm 계산
val norm: (Point) -> Double = { point -> sqrt((point[0] * point[0] + point[1] * point[1]).toDouble()) }

// 4. 모든 norm을 리스트로 반환
val normAll = { points: PointList -> points.map { norm(it) } }

// 5. 2보다 작은 norm 필터링
val normLessThanTwo: (List<Double>) -> List<Double> = { norms -> norms.filter { it < 2 } }

// 6. X좌표가 음수인 점을 Y축 기준으로 좌우 대칭
fun flipX(points: PointList): PointList {
    return points.map {
        if (it[0] < 0) listOf(-it[0], it[1]) else it
    }
}

fun main() {

    val points = listOf(pt(1, 2), pt(-2, 3), pt(-1, 0), pt(2, 1))

    print("original points: \t")
    process(points)

    print("left points: \t")
    process(points, ::filterLeft)

    print("norms: \t")
    process(points) { pts -> normAll(pts) }

    print("norm<2: \t")
    process(points) { pts -> normLessThanTwo(normAll(pts)) }

    print("flip_x: \t")
    process(points, ::flipX)
}