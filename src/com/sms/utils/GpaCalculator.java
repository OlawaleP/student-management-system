package com.sms.utils;

import java.util.Map;

public class GpaCalculator {
  // Letter grade to grade point conversion
  private static final Map<String, Double> GRADE_POINTS = Map.of(
  );
  
  /**
   * Converts a numeric grade to its letter equivalent
   */
  public static String getLetterGrade(double grade) {
      if (grade >= 97) return "A+";
      if (grade >= 93) return "A";
      if (grade >= 90) return "A-";
      if (grade >= 87) return "B+";
      if (grade >= 83) return "B";
      if (grade >= 80) return "B-";
      if (grade >= 77) return "C+";
      if (grade >= 73) return "C";
      if (grade >= 70) return "C-";
      if (grade >= 67) return "D+";
      if (grade >= 60) return "D";
      return "F";
  }
  
  /**
   * Converts a numeric grade to its grade point equivalent
   */
  public static double getGradePoint(double grade) {
      String letterGrade = getLetterGrade(grade);
      return GRADE_POINTS.getOrDefault(letterGrade, 0.0);
  }
}
