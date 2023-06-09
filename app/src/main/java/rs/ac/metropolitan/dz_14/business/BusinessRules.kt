package rs.ac.metropolitan.dz_14.business

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import rs.ac.metropolitan.dz_14.data.Activity
import rs.ac.metropolitan.dz_14.data.Company
import rs.ac.metropolitan.dz_14.data.Traffic

@OptIn(ExperimentalCoroutinesApi::class)
suspend fun Flow<List<Company>>.getPriority(forActivity: Activity): Flow<List<Company>> {

    return when (forActivity) {
        Activity.IT -> {
            return flowOf(this.flatMapConcat { it.asFlow() }.toList()
                .filter { it.activity == forActivity }
            )
        }
        Activity.MANAGEMENT -> {
            return flowOf(this.flatMapConcat { it.asFlow() }.toList()
                .filter { it.activity == forActivity })
        }
        Activity.DESIGN -> {
            return flowOf(this.flatMapConcat { it.asFlow() }.toList()
                .filter { it.activity == forActivity })
        }

        else -> {
            return flowOf(this.flatMapConcat { it.asFlow() }.toList()
                .filter { it.activity == forActivity })
        }
    }
}