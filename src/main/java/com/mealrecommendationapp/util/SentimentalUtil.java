package com.mealrecommendationapp.util;

import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations;

import java.util.Properties;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.CoreMap;

public final class SentimentalUtil {

        public static float getSentimentRate(String line) {
            Properties props = new Properties();
            props.setProperty("annotators", "tokenize, ssplit, parse, sentiment");
            StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
            float mainSentiment = 0;
            if (line != null && line.length() > 0) {
                int longest = 0;
                Annotation annotation = pipeline.process(line);
                for (CoreMap sentence : annotation.get(CoreAnnotations.SentencesAnnotation.class)) {
                    Tree tree = sentence.get(SentimentCoreAnnotations.SentimentAnnotatedTree.class);
                    int sentiment = RNNCoreAnnotations.getPredictedClass(tree);
                    String partText = sentence.toString();
                    if (partText.length() > longest) {
                        mainSentiment = sentiment;
                        longest = partText.length();
                    }
                }
            }
            return mainSentiment + 1;
        }

        public static void main(String[] args) {
            String review = "I love this meal so much";
            float rate = SentimentalUtil.getSentimentRate(review);
            System.out.println(review + " : " + rate);
            review = "Nice meal";
            rate = SentimentalUtil.getSentimentRate(review);
            System.out.println(review + " : " + rate);
            review = "my name is very good";
            rate = SentimentalUtil.getSentimentRate(review);
            System.out.println(review + " : " + rate);
        }
    }
