# ============================================================
# EMPLOYEE BURNOUT RISK PREDICTION SYSTEM
# Machine Learning Internship Assignment
# ============================================================

# ==========================
# 1. IMPORT LIBRARIES
# ==========================

import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
import seaborn as sns

from sklearn.model_selection import train_test_split
from sklearn.linear_model import LinearRegression
from sklearn.metrics import mean_absolute_error
from sklearn.metrics import mean_squared_error
from sklearn.metrics import r2_score

# ==========================
# 2. LOAD DATASET
# ==========================

df = pd.read_csv("employee_burnout_dataset_1000_records.csv")

print("Dataset Loaded Successfully")
print("\nFirst Five Records:")
print(df.head())

# ==========================
# 3. DATASET OVERVIEW
# ==========================

print("\nDataset Shape:")
print(df.shape)

print("\nDataset Information:")
print(df.info())

print("\nMissing Values:")
print(df.isnull().sum())

print("\nStatistical Summary:")
print(df.describe())

# ==========================
# 4. EXPLORATORY DATA ANALYSIS
# ==========================

# Correlation Heatmap

plt.figure(figsize=(12,8))
sns.heatmap(df.corr(numeric_only=True),
            annot=True,
            cmap="coolwarm")
plt.title("Correlation Heatmap")
plt.show()

# Histograms

df.hist(figsize=(15,12))
plt.suptitle("Feature Distributions")
plt.tight_layout()
plt.show()

# Boxplots

numeric_columns = df.select_dtypes(include=np.number).columns

for col in numeric_columns:

    plt.figure(figsize=(8,3))
    sns.boxplot(x=df[col])

    plt.title(f"Boxplot - {col}")
    plt.show()

# Scatterplots with target

target = "burnout_risk_score"

for col in numeric_columns:

    if col != target:

        plt.figure(figsize=(6,4))

        plt.scatter(df[col], df[target])

        plt.xlabel(col)
        plt.ylabel(target)

        plt.title(f"{col} vs Burnout Risk")

        plt.show()

# ==========================
# 5. DATA PREPROCESSING
# ==========================

# Remove Employee ID

if "employee_id" in df.columns:
    df.drop("employee_id", axis=1, inplace=True)

print("\nColumns after preprocessing:")
print(df.columns)

# ==========================
# 6. FEATURES AND TARGET
# ==========================

X = df.drop("burnout_risk_score", axis=1)

y = df["burnout_risk_score"]

# ==========================
# 7. TRAIN TEST SPLIT
# ==========================

X_train, X_test, y_train, y_test = train_test_split(
    X,
    y,
    test_size=0.20,
    random_state=42
)

print("\nTraining Records :", len(X_train))
print("Testing Records :", len(X_test))

# ==========================
# 8. MODEL BUILDING
# ==========================

model = LinearRegression()

model.fit(X_train, y_train)

print("\nLinear Regression Model Trained Successfully")

# ==========================
# 9. PREDICTIONS
# ==========================

y_pred = model.predict(X_test)

# ==========================
# 10. MODEL EVALUATION
# ==========================

mae = mean_absolute_error(y_test, y_pred)

mse = mean_squared_error(y_test, y_pred)

rmse = np.sqrt(mse)

r2 = r2_score(y_test, y_pred)

print("\n===== MODEL EVALUATION =====")

print("MAE  :", round(mae,4))
print("MSE  :", round(mse,4))
print("RMSE :", round(rmse,4))
print("R2   :", round(r2,4))

# ==========================
# 11. ACTUAL VS PREDICTED
# ==========================

plt.figure(figsize=(8,6))

plt.scatter(y_test, y_pred)

plt.xlabel("Actual Burnout Score")

plt.ylabel("Predicted Burnout Score")

plt.title("Actual vs Predicted Burnout Score")

plt.show()

# ==========================
# 12. REGRESSION COEFFICIENTS
# ==========================

coefficients = pd.DataFrame(
    {
        "Feature": X.columns,
        "Coefficient": model.coef_
    }
)

coefficients["Absolute"] = abs(coefficients["Coefficient"])

coefficients = coefficients.sort_values(
    by="Absolute",
    ascending=False
)

print("\nFeature Importance")
print(coefficients)

# Plot

plt.figure(figsize=(10,6))

sns.barplot(
    x="Coefficient",
    y="Feature",
    data=coefficients
)

plt.title("Feature Importance")

plt.show()

# ==========================
# 13. TOP HIGH-RISK EMPLOYEES
# ==========================

all_predictions = model.predict(X)

df["Predicted_Burnout_Risk"] = all_predictions

high_risk = df.sort_values(
    by="Predicted_Burnout_Risk",
    ascending=False
)

print("\nTop 10 High Risk Employees")

print(
    high_risk[
        [
            "Predicted_Burnout_Risk"
        ]
    ].head(10)
)

# ==========================
# 14. BUSINESS INSIGHTS
# ==========================

print("\n===== BUSINESS INSIGHTS =====")

print("""
1. Features with larger coefficients
   have greater impact on burnout.

2. Positive coefficient:
   increases burnout risk.

3. Negative coefficient:
   reduces burnout risk.

4. Employees with high stress,
   more projects and longer work
   hours are more vulnerable.

5. Sleep and exercise help reduce
   burnout risk.
""")

# ==========================
# 15. SCENARIO ANALYSIS
# ==========================

print("\n===== SCENARIO ANALYSIS =====")

original_average = np.mean(model.predict(X))

print("\nCurrent Average Burnout:")
print(round(original_average,2))

# Scenario 1
scenario1 = X.copy()

if "weekly_work_hours" in scenario1.columns:

    scenario1["weekly_work_hours"] *= 0.90

new_average1 = np.mean(model.predict(scenario1))

print("\nScenario 1")
print("Reduce Weekly Work Hours by 10%")

print("New Average Burnout:",
      round(new_average1,2))

print("Change:",
      round(original_average - new_average1,2))

# Scenario 2
scenario2 = X.copy()

if "sleep_hours" in scenario2.columns:

    scenario2["sleep_hours"] += 1

new_average2 = np.mean(model.predict(scenario2))

print("\nScenario 2")
print("Increase Sleep by 1 Hour")

print("New Average Burnout:",
      round(new_average2,2))

print("Change:",
      round(original_average - new_average2,2))

# Scenario 3
scenario3 = X.copy()

if "exercise_hours_week" in scenario3.columns:

    scenario3["exercise_hours_week"] += 3

new_average3 = np.mean(model.predict(scenario3))

print("\nScenario 3")
print("Increase Exercise by 3 Hours")

print("New Average Burnout:",
      round(new_average3,2))

print("Change:",
      round(original_average - new_average3,2))

# ==========================
# 16. SAVE RESULTS
# ==========================

results = pd.DataFrame(
    {
        "Actual": y_test,
        "Predicted": y_pred
    }
)

results.to_csv(
    "burnout_predictions.csv",
    index=False
)

print("\nPrediction file saved successfully")

print("\nPROJECT COMPLETED SUCCESSFULLY")