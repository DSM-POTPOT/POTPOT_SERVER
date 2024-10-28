package com.example.potpot.domain.apply.sevice

import com.example.potpot.domain.apply.domain.Apply
import com.example.potpot.domain.apply.domain.ApplyRepository
import com.example.potpot.domain.apply.presentation.dto.request.ApplyRequest
import com.example.potpot.domain.feed.domain.FeedRepository
import com.example.potpot.domain.user.facade.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ApplyService(
    private val userFacade: UserFacade,
    private val applyRepository: ApplyRepository,
    private val feedRepository: FeedRepository
) {
    @Transactional
    fun execute(request: ApplyRequest) {
        val user = userFacade.getCurrentUser()
        val feed = feedRepository.findById(request.feedId).get()

        applyRepository.save(
            Apply(
                user = user,
                feed = feed
            )
        )
    }
}
