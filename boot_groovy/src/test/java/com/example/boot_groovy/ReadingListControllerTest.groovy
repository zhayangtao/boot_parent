package com.example.boot_groovy

import org.junit.Test

import static org.mockito.Mockito.*


/**
 * @author zhayangtao* @version 1.0* @since 2019/01/30
 */
class ReadingListControllerTest {

    @Test
    void shouldReturnReadingListFromRepository() {
        List<Book> expectedList = new ArrayList<>()
        expectedList.add(new Book(id: 1, reader: "Craig", isbn: "99999", title: "Spring boot",
                author: "walls", description: "is a..."))

        def mockRepo = mock(ReadingListRepository.class)
        when(mockRepo.findByReader("Craig")).thenReturn(expectedList)

//        def controller = new ReadingListController()
    }
}
