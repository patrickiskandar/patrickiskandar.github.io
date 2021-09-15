SELECT name AS DRUG NAME,
  (SELECT COUNT(DISTINCT a.patientid)
  FROM administer a, patients p
  WHERE p.patientid = a.patientid
  AND p.status = 'recovered'
  ) AS RECOVERED,
  (SELECT (COUNT(DISTINCT a.patientid) - recovered)
  FROM administer a, patients p
  WHERE p.patientid = a.patientid
  ) AS NOTRECOVERED
FROM administer
GROUP BY name;;
