package ci.ashamaz.hashtagsubscriber.util.extention

import java.util.*

fun String.shorten(maxSize: Int = 1400): String {
    if (this.length <= maxSize) return this
    else {
        var result = this.substring(0, maxSize) + "..."
        if (bracketsClosed(result)) return result
        else {
            val index = result.lastIndexOf('<')
            return result.substring(0, index-1)+"..."
        }
    }

}

fun bracketsClosed(text: String): Boolean {
    val stack: Stack<Char> = Stack<Char>()
    for (element in text) {
        val symbol = element
        if (symbol == '<') stack.push(symbol)
        else if (symbol == '>') {
            if (stack.empty()) return false
            if (stack.pop() != '<') return false
        }
    }
    return stack.empty()
}

private fun indexOf(symbol: Char, array: CharArray?): Int {
    requireNotNull(array)
    var index = -1
    for (i in array.indices) {
        if (array[i] == symbol) index = i
    }
    return index
}