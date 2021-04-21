package com.fongfuse.githubdemo.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.fongfuse.githubdemo.data.BaseResponse
import com.fongfuse.githubdemo.data.SearchResponse
import com.fongfuse.githubdemo.repository.GithubRepository
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.*


@ExperimentalCoroutinesApi
class MainViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @MockK
    lateinit var repository: GithubRepository

    private val testCoroutineDispatcher = TestCoroutineDispatcher()
    private lateinit var viewModel: MainViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(testCoroutineDispatcher)
        viewModel = spyk(MainViewModel(repository))
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testCoroutineDispatcher.cleanupTestCoroutines()
        clearAllMocks()
    }

    @Test
    fun `getSearch success `() = runBlocking {

        val search = "fongfuse"
        val response: SearchResponse = mockResponse()

        coEvery {
            repository.search(any())
        } returns flow {
            emit(response)
        }

        val loading = mutableListOf<Boolean>()
        viewModel.loading.observeForever { loading.add(it) }

        viewModel.callSearchUsers(search)
        Assert.assertEquals(viewModel.items.value, response.items)

        verify { viewModel.showLoading() }
        verify { viewModel.hideLoading() }
        Assert.assertEquals(loading[0], true)
        Assert.assertEquals(loading[1], false)

        Assert.assertEquals(viewModel.items.value?.size, 2)
    }

    @Test
    fun `itemsIsEmpty == true`() = runBlockingTest {
        every { viewModel.itemsIsEmpty() } returns true
        val hasData = viewModel.itemsIsEmpty()

        Assert.assertEquals(hasData,true)
    }

    @Test
    fun `itemsIsEmpty == false`() = runBlockingTest {
        every { viewModel.itemsIsEmpty() } returns false
        val hasData = viewModel.itemsIsEmpty()
        Assert.assertEquals(hasData,false)
    }


    private fun mockResponse(): SearchResponse {
        val item = BaseResponse(
            login = "Fongfuse",
            avatar_url = "https://image.shutterstock.com/image-vector/default-avatar-profile-icon-grey-260nw-518740753.jpg"
        )
        return SearchResponse(
            incomplete_results = null,
            items = listOf(item, item),
            total_count = 20)
    }
}