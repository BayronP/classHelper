package com.vote.questionnaire;

import java.util.ArrayList;

public class DataLoader {

	VoteSubmitItem testItem;
	ArrayList<VoteSubmitItem> testItems;
	String[] voteQuestion = { "��զ���ձ����ల�������ΰݾ������磿", "�����¹�Ƶ�����㻹�����������", "үү֧�����Ӳ���ѧ�Ʋ�Ǩ��࣬զ����", "�°桶�����˲����Ƿ忸���壬��զ����", "���괺��,����ô�ؼң�", "��զ����ʮ�򱱾��ϰ���ÿ�տ�ʡ�ϰࣿ", "��������Ͷ˲�ҵ��������զ����" , "�㰮����������զ����"};
	String[][] voteAnswers = { { "1.���飬��ս���겻�ܰ�Ϣ", "2.�侲���˼��м��ݵ�����"}, { "1.�������ý��ֻ���Ҫ����", "2.���£��ܾ���������", "3.����֣�����Ҫ�ý��ڵ�", "4.��֪����ֻ������鴦���" }, { "1.��Ľ�������ܶ���ҲûǮ", "2.�ɱ�������������̬�ٺ���", "3.���棬�����������������", "4.·��������̬" }, { "1.������ӹ����̣����", "2.˧����Ů�����������˿�ζ", "3.�����������˲�����", "4.������ʱ�仹�а�", "5.�׾���У�����ɶ" }, { "1.�����٣�ֻ�ܶ໨Ǯ���ɻ�", "2.�������Ʊ", "3.�ϼҴ��ڵģ������ذ�", "4.�Ӱ࣬�ز��˼�", "5.�Ҿ��ڼ��繤����ͬ������" }, { "1.���ᣬ���ı����ż٣�", "2.��ã������ڱ�����ɶ��", "3.ֻ��˵���Լ�ѡ������ʽ", "4.·�����Ҳ��ڱ���" }, { "1.����������Ҫ��չ��8���ˡ�", "2.�۶�����ʳ�˼��̻�ģ�", "3.����еͶ˲�ҵ��", "4.����������" },{ "1.���飬��ս���겻�ܰ�Ϣ", "2.�侲���˼��м��ݵ�����", "3.�����ˣ���ľ��" } };

	/**
	 * @return ��������
	 */
	public ArrayList<VoteSubmitItem> getTestData() {

		testItems = new ArrayList<VoteSubmitItem>();
		for (int i = 0; i < 8; i++) {
			testItem = new VoteSubmitItem();
			testItem.itemId = i;
			testItem.voteQuestion = voteQuestion[i];
			for (int j = 0; j < voteAnswers[i].length; j++) {
				testItem.voteAnswers.add(voteAnswers[i][j]);
			}
			testItems.add(testItem);
		}
		return testItems;
	}

}
