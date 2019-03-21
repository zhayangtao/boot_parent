package com.example.boot_groovy

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @author zhayangtao* @version 1.0* @since 2019/01/30
 */
@RestController
@RequestMapping("/")
class ReadingListController {
    String reader = "Craig"

    @Autowired
    ReadingListRepository readingListRepository

    @GetMapping
    def readersBooks(Model model) {
        List<Book> readingList = readingListRepository.findByReader(reader)

        if (readingList) {
            model.addAttribute("books", readingList)
        }
        "readingList"
    }

    @PostMapping
    def addToReadingList(Book book) {
        book.setReader(reader)
        readingListRepository.save(book)
        "redirect:/"
    }
}
