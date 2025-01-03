package com.example.potpot.domain.feed.service

import com.example.potpot.domain.feed.domain.Feed
import com.example.potpot.domain.feed.domain.FeedRepository
import com.example.potpot.domain.feed.presentation.dto.request.FeedRequest
import com.example.potpot.domain.user.facade.UserFacade
import com.example.potpot.global.utils.S3Utils
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile

@Service
class CreateFeedService(
    private val userFacade: UserFacade,
    private val feedRepository: FeedRepository,
    private val s3Utils: S3Utils
) {

    @Transactional
    fun execute(image: MultipartFile?, request: FeedRequest) {
        val user = userFacade.getCurrentUser()

        val images = image?.let { s3Utils.saveFile(it) }

        feedRepository.save(
            Feed(
                title = request.title,
                content = request.content,
                date = request.date,
                category = request.category,
                image = images,
                user = user
            )
        )
    }
}
