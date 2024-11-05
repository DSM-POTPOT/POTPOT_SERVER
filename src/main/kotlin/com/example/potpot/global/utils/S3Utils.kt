package com.example.potpot.global.utils

import com.amazonaws.services.s3.AmazonS3Client
import com.amazonaws.services.s3.model.ObjectMetadata
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import java.util.*

@Component
class S3Utils(
    private val amazonS3: AmazonS3Client,

    @Value("\${cloud.aws.s3.bucket}")
    private val bucketName: String
) {
    fun saveFile(multipartFile: MultipartFile): String {
        val originalFilename = UUID.randomUUID().toString()

        val metadata = ObjectMetadata().apply {
            contentLength = multipartFile.size
            contentType = multipartFile.contentType
        }

        amazonS3.putObject(bucketName, originalFilename, multipartFile.inputStream, metadata)
        return amazonS3.getUrl(bucketName, originalFilename).toString()
    }

    fun delete(objectName: String) {
        amazonS3.deleteObject(bucketName, objectName)
    }
}
