package me.hao0.basic.concurrent.forkjoin;

import java.util.concurrent.RecursiveTask;

/**
 * 文档计数任务 上午8:03:31
 */
class DocumentSearchTask extends RecursiveTask<Long> {

	private static final long serialVersionUID = 3316064994068474147L;
	private final Document document;
	private final String searchedWord;

	DocumentSearchTask(Document document, String searchedWord) {
		super();
		this.document = document;
		this.searchedWord = searchedWord;
	}

	@Override
	protected Long compute() {
		return WordCounter.occurrencesCount(document, searchedWord);
	}
}