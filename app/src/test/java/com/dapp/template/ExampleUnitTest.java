/*
 *
 *  * Copyright 2018 Mark Dappollone
 *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 *
 */

package com.dapp.template;

import com.dapp.template.model.MainActivityModel;
import com.dapp.template.model.RelatedTopic;
import com.dapp.template.model.SearchResults;
import com.dapp.template.presenter.MainPresenter;
import com.dapp.template.utils.Schedulers;
import com.dapp.template.view.MainView;
import io.reactivex.Observable;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

@RunWith(MockitoJUnitRunner.class)
public class ExampleUnitTest {
    @Mock MainActivityModel model;
    @Mock SearchResults searchResults;
    @Mock RelatedTopic relatedTopic;
    @Mock MainView mainView;
    @Mock Schedulers schedulers;

    MainPresenter presenter;

    @Before
    public void setup() {
        when(schedulers.getIoThread()).thenReturn(io.reactivex.schedulers.Schedulers.trampoline());
        when(schedulers.getMainThread()).thenReturn(io.reactivex.schedulers.Schedulers.trampoline());

        presenter = new MainPresenter(model, schedulers);
        presenter.attachView(mainView);
    }

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void valid_data_presents_correctly() {
        when(relatedTopic.getText()).thenReturn("text");
        List<RelatedTopic> relatedTopics = new ArrayList<>();
        relatedTopics.add(relatedTopic);

        when(searchResults.getRelatedTopics()).thenReturn(relatedTopics);
        when(model.loadData(anyString())).thenReturn(Observable.just(searchResults));

        presenter.onResume();
        verify(mainView).setMessageText(anyString());
    }

    @Test
    public void empty_data_presents_correctly() {
        List<RelatedTopic> relatedTopics = new ArrayList<>();

        when(searchResults.getRelatedTopics()).thenReturn(relatedTopics);
        when(model.loadData(anyString())).thenReturn(Observable.just(searchResults));

        presenter.onResume();
        verify(mainView, never()).setMessageText(anyString());
    }

    static final String json = "{\"Definition\":\"\",\"Image\":\"\",\"Abstract\":\"\",\"Answer\":\"\",\"Entity\":\"\",\"DefinitionURL\":\"\",\"Heading\":\"Seinfeld\",\"AnswerType\":\"\",\"RelatedTopics\":[{\"FirstURL\":\"https://duckduckgo.com/Seinfeld\",\"Result\":\"<a href=\\\"https://duckduckgo.com/Seinfeld\\\">Seinfeld</a> An American sitcom that originally ran for nine seasons on NBC, from 1989 to 1998.\",\"Icon\":{\"Height\":\"\",\"Width\":\"\",\"URL\":\"https://duckduckgo.com/i/ba1bdc02.png\"},\"Text\":\"Seinfeld An American sitcom that originally ran for nine seasons on NBC, from 1989 to 1998.\"},{\"FirstURL\":\"https://duckduckgo.com/Jerry_Seinfeld\",\"Result\":\"<a href=\\\"https://duckduckgo.com/Jerry_Seinfeld\\\">Jerry Seinfeld</a> An American comedian, actor, director, writer, and producer.\",\"Text\":\"Jerry Seinfeld An American comedian, actor, director, writer, and producer.\",\"Icon\":{\"URL\":\"https://duckduckgo.com/i/0f4b32e0.jpg\",\"Width\":\"\",\"Height\":\"\"}},{\"Icon\":{\"Width\":\"\",\"URL\":\"\",\"Height\":\"\"},\"Text\":\"\\\"Seinfeld\\\" (Curb Your Enthusiasm) The tenth and final episode of the seventh season of American situation comedy Curb Your Enthusiasm.\",\"Result\":\"<a href=\\\"https://duckduckgo.com/Seinfeld_(Curb_Your_Enthusiasm)\\\">\\\"Seinfeld\\\" (Curb Your Enthusiasm)</a>The tenth and final episode of the seventh season of American situation comedy Curb Your Enthusiasm.\",\"FirstURL\":\"https://duckduckgo.com/Seinfeld_(Curb_Your_Enthusiasm)\"},{\"Name\":\"People\",\"Topics\":[{\"FirstURL\":\"https://duckduckgo.com/Jerry_Seinfeld_(character)\",\"Result\":\"<a href=\\\"https://duckduckgo.com/Jerry_Seinfeld_(character)\\\">Jerry Seinfeld (character)</a>The protagonist of the American television sitcom Seinfeld.\",\"Text\":\"Jerry Seinfeld (character) The protagonist of the American television sitcom Seinfeld.\",\"Icon\":{\"Height\":\"\",\"URL\":\"https://duckduckgo.com/i/4dd507db.jpg\",\"Width\":\"\"}}]}],\"ImageIsLogo\":0,\"AbstractSource\":\"Wikipedia\",\"Results\":[],\"DefinitionSource\":\"\",\"Redirect\":\"\",\"Type\":\"D\",\"Infobox\":\"\",\"meta\":{\"src_id\":1,\"src_domain\":\"en.wikipedia.org\",\"developer\":[{\"url\":\"http://www.duckduckhack.com\",\"name\":\"DDG Team\",\"type\":\"ddg\"}],\"name\":\"Wikipedia\",\"live_date\":null,\"description\":\"Wikipedia\",\"maintainer\":{\"github\":\"duckduckgo\"},\"signal_from\":\"wikipedia_fathead\",\"production_state\":\"online\",\"repo\":\"fathead\",\"perl_module\":\"DDG::Fathead::Wikipedia\",\"dev_date\":null,\"src_name\":\"Wikipedia\",\"blockgroup\":null,\"status\":\"live\",\"src_options\":{\"is_wikipedia\":1,\"skip_qr\":\"\",\"src_info\":\"\",\"skip_image_name\":0,\"min_abstract_length\":\"20\",\"is_fanon\":0,\"is_mediawiki\":1,\"skip_abstract_paren\":0,\"skip_abstract\":0,\"language\":\"en\",\"skip_end\":\"0\",\"skip_icon\":0,\"source_skip\":\"\",\"directory\":\"\"},\"is_stackexchange\":null,\"topic\":[\"productivity\"],\"unsafe\":0,\"designer\":null,\"src_url\":null,\"created_date\":null,\"id\":\"wikipedia_fathead\",\"dev_milestone\":\"live\",\"js_callback_name\":\"wikipedia\",\"example_query\":\"nikola tesla\",\"attribution\":null,\"tab\":\"About\",\"producer\":null},\"ImageWidth\":0,\"ImageHeight\":0,\"AbstractText\":\"\",\"AbstractURL\":\"https://en.wikipedia.org/wiki/Seinfeld_(disambiguation)\"}";
}