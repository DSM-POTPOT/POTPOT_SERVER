package com.example.potpot.domain.feed.service

import com.example.potpot.domain.feed.domain.FeedRepository
import com.example.potpot.domain.feed.exception.FeedNotFoundException
import com.example.potpot.domain.feed.presentation.dto.request.FeedRequest
import com.example.potpot.domain.user.exception.UserMismatchException
import com.example.potpot.domain.user.facade.UserFacade
import com.example.potpot.global.utils.S3Utils
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile

@Service
class ModifyFeedService(
    private val feedRepository: FeedRepository,
    private val userFacade: UserFacade,
    private val s3Utils: S3Utils
) {

    @Transactional
    fun execute(id: Long, request: FeedRequest, file: MultipartFile?) {
        val user = userFacade.getCurrentUser()
        val feed = feedRepository.findById(id).orElseThrow() ?: throw FeedNotFoundException

        if (user.id != feed.user.id) throw UserMismatchException
        val image = handleImage(feed.image, file)

        feed.modifyFeed(request.title, request.content, request.date, request.category, image)
    }

    private fun handleImage(image: String?, newImage: MultipartFile?): String? {
        image?.let { s3Utils.delete(it) }
        return if (newImage == null) {
            null
        } else {
            s3Utils.saveFile(newImage)
        }
    }
}
