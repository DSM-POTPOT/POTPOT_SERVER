package com.example.potpot.domain.apply.sevice

import com.example.potpot.domain.apply.domain.ApplyRepository
import com.example.potpot.domain.apply.presentation.dto.response.ApplyResponse
import com.example.potpot.domain.user.facade.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class QueryMyApplyService(
    private val userFacade: UserFacade,
    private val applyRepository: ApplyRepository
) {
    @Transactional(readOnly = true)
    fun execute(): List<ApplyResponse> {
        val user = userFacade.getCurrentUser()
        val applies = applyRepository.findAllByUser(user)

        return applies.map {
            ApplyResponse(
                id = it.id,
                feedId = it.feed.id,
                isOK = it.isOK
            )
        }.sortedByDescending { it.id }
    }
}
