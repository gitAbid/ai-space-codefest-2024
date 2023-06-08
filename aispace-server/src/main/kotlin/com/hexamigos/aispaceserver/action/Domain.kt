package com.hexamigos.aispaceserver.action

import java.util.*

enum class ChainState {
    NEXT, SKIP, ERROR, ABORT, FINISHED
}

data class ActionChain<T>(var state: ChainState = ChainState.NEXT, var content: T) {
    fun hasNext() = state == ChainState.NEXT
}

open class ActionResponse<I, O>(val original: I, val processed: O) {
    override fun toString(): String {
        return "ActionResponse(original=$original, processed=$processed)"
    }
}

class Processed<I, O>(original: I, processed: O) : ActionResponse<I, O>(original, processed) {

}

class Transformed<I, O>(original: I, processed: O) : ActionResponse<I, O>(original, processed)
class Executed<I, O>(original: I, processed: O) : ActionResponse<I, O>(original, processed)

abstract class Approval(
        open var id: String = UUID.randomUUID().toString(),
        open val approve: () -> Unit,
        open val reject: () -> Unit
)